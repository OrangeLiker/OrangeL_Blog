package org.orange.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.orange.constans.SystemConstants;
import org.orange.domain.dto.ChangePasswordDto;
import org.orange.domain.dto.StatusDto;
import org.orange.domain.dto.UserDto;
import org.orange.domain.entity.Role;
import org.orange.domain.entity.User;
import org.orange.domain.entity.UserRole;
import org.orange.domain.enums.AppHttpCodeEnum;
import org.orange.domain.response.ResponseResult;
import org.orange.domain.vo.AdminUserVo;
import org.orange.domain.vo.PageVo;
import org.orange.domain.vo.UserInfoVo;
import org.orange.exception.SystemException;
import org.orange.mapper.RoleMapper;
import org.orange.mapper.UserMapper;
import org.orange.mapper.UserRoleMapper;
import org.orange.service.UserService;
import org.orange.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.Map.*;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/5
 * @Version: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailValidator emailValidator;
    @Autowired
    private PwValidator pwValidator;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult userInfo() {
        Long userId = SecurityUtils.getUserId();
        if(userId == null){
            throw new RuntimeException("用户未登录");
        }
        User user = getById(userId);
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }
    //修改用户信息,简单修改
    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult register(User user) {
        //对数据进行重复性判断
        if(userNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(userEmailExist(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        //校验验证码
        String validaCode=user.getValidaCode();
        if(validaCode==null||"".equals(validaCode)){
            return ResponseResult.errorResult(AppHttpCodeEnum.REQUIRE_VALIDACODE);
        }
        String redisCode=redisCache.getCacheObject(SystemConstants.VERIFY_EMAIL_DATA+user.getEmail());
        if(redisCode==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.CODE_EXPIRED);
        }
        if(!redisCode.equals(validaCode)){
            return ResponseResult.errorResult(AppHttpCodeEnum.CODE_WRONG);
        }
        //密码加密处理
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateTime(new Date());
        //存入数据库
        save(user);
        redisCache.deleteObject(SystemConstants.VERIFY_EMAIL_DATA+user.getEmail());
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getUserList(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status) {
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        if(userName!=null){
            queryWrapper.like(User::getUserName,userName);
        }
        if(phonenumber!=null){
            queryWrapper.like(User::getPhonenumber,phonenumber);
        }
        if(status != null && !"".equals(status)){
            queryWrapper.eq(User::getStatus,status);
        }
        queryWrapper.eq(User::getDelFlag, SystemConstants.NOR);
        List<User> userList=userMapper.selectList(queryWrapper);
        Page<User> page=new Page<>(pageNum,pageSize);
        page.setRecords(userList);
        page.setTotal(userList.size());
        PageVo pageVo=new PageVo();
        pageVo.setRows(page.getRecords());
        pageVo.setTotal(page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addUser(UserDto userDto) {
        if(userDto.getUserName()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        if(userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName,userDto.getUserName()))!=null){
            return ResponseResult.errorResult(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhonenumber,userDto.getPhonenumber()))!=null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PHONENUMBER_EXIST);
        }
        if(userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getEmail,userDto.getEmail()))!=null){
            return ResponseResult.errorResult(AppHttpCodeEnum.EMAIL_EXIST);
        }
        if(!emailValidator.isValidEmail(userDto.getEmail())){
            return ResponseResult.errorResult(AppHttpCodeEnum.EMAIL_FORMAT_ERROR);
        }
        if(userDto.getPassword()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.REQUIRE_PASSWORD);
        }
        if(!pwValidator.isValidPassword(userDto.getPassword())){
            return ResponseResult.errorResult(AppHttpCodeEnum.PASSWORD_FORMAT_ERROR);
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user=BeanCopyUtils.copyBean(userDto,User.class);
        user.setCreateBy(SecurityUtils.getUserId());
        user.setCreateTime(new Date());
        save(user);
        List<Long> roleIds=userDto.getRoleIds();
        for (Long roleId:roleIds){
            UserRole userRole=new UserRole(user.getId(), roleId);
            userRoleMapper.insert(userRole);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteUser(List<Long> id) {
        for (Long userId:id){
            User user=userMapper.selectById(userId);
            user.setDelFlag(SystemConstants.DELETE);
            userMapper.updateById(user);
            userRoleMapper.delete(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId,userId));
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getUser(Long id) {
        AdminUserVo adminUserVo=new AdminUserVo();
        adminUserVo.setUser(userMapper.selectById(id));
        LambdaQueryWrapper<UserRole> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId,id);
        List<Long> roleIds=userRoleMapper.selectList(queryWrapper).stream()
                                         .map(UserRole::getRoleId)
                                         .collect(Collectors.toList());
        adminUserVo.setRoleIds(roleIds);
        List<Role> roles=roleMapper.selectList(null);
        adminUserVo.setRoles(roles);
        return ResponseResult.okResult(adminUserVo);
    }

    @Override
    public ResponseResult updateUser(UserDto userDto) {
        String newPhone=userDto.getPhonenumber();
        if(newPhone!=null){
            String oldPhone=userMapper.selectById(userDto.getId()).getPhonenumber();
            if(!userDto.getPhonenumber().equals(oldPhone)){
                if(userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhonenumber,userDto.getPhonenumber()))!=null){
                    return ResponseResult.errorResult(AppHttpCodeEnum.PHONENUMBER_EXIST);
                }
            }
        }
        String oldEmail=userMapper.selectById(userDto.getId()).getEmail();
        //如果邮箱不变，不用判断
        if(!userDto.getEmail().equals(oldEmail)){
            if(userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getEmail,userDto.getEmail()))!=null) {
                return ResponseResult.errorResult(AppHttpCodeEnum.EMAIL_EXIST);
            }
        }
        if(!emailValidator.isValidEmail(userDto.getEmail())){
            return ResponseResult.errorResult(AppHttpCodeEnum.EMAIL_FORMAT_ERROR);
        }
        User user=BeanCopyUtils.copyBean(userDto,User.class);
        user.setUpdateBy(SecurityUtils.getUserId());
        user.setUpdateTime(new Date());
        userMapper.updateById(user);
        userRoleMapper.delete(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId,user.getId()));
        List<Long> roleIds=userDto.getRoleIds();
        for (Long roleId:roleIds){
            UserRole userRole=new UserRole(user.getId(), roleId);
            userRoleMapper.insert(userRole);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult changeStatus(StatusDto status) {
        User user=userMapper.selectById(status.getUserId());
        user.setStatus(status.getStatus());
        user.setUpdateTime(new Date());
        user.setUpdateBy(SecurityUtils.getUserId());
        userMapper.updateById(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult changePassword(ChangePasswordDto changePasswordDto) {
        if(changePasswordDto == null){
            throw new SystemException(AppHttpCodeEnum.RESET_PASSWORD_NOT_NULL);
        }
        User dbUser = userMapper.selectById(changePasswordDto.getUserId());
        if(dbUser == null){
            throw new SystemException(AppHttpCodeEnum.USER_NOT_FOUND);
        }
        String dbOldPassword = dbUser.getPassword();
        System.out.println(dbOldPassword);
        if(!passwordEncoder.matches(changePasswordDto.getOldPassword(),dbOldPassword)){
            throw new SystemException(AppHttpCodeEnum.ORIGIN_PASSWORD_ERROR);
        }
        if(passwordEncoder.encode(changePasswordDto.getNewPassword()).equals(dbOldPassword)){
            throw new SystemException(AppHttpCodeEnum.NEW_PASSWORD_SAME);
        }
        dbUser.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        userMapper.updateById(dbUser);
        //在Redis中删除当前登录用户的信息，使其重新登录
        redisCache.deleteObject("bloglogin:"+changePasswordDto.getUserId());
        return ResponseResult.okResult();
    }


    //对数据进行重复性判断
    private boolean userNameExist(String userName){
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,userName);
        return count(queryWrapper)>0?true:false;
    }
    private boolean userEmailExist(String email){
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail,email);
        return count(queryWrapper)>0?true:false;
    }

    //比较两个List集合是否完全相同
    private boolean compareList(List<Long> oldRoleIds, List<Long> newRoleIds) {
        if(oldRoleIds.size()!=newRoleIds.size()){
            return false;
        }
        for (Long oldRoleId:oldRoleIds){
            if(!newRoleIds.contains(oldRoleId)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder1 = new BCryptPasswordEncoder();
        String old="4310520";
        System.out.println(passwordEncoder1.encode(old));

    }
}
