package org.orange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.orange.constans.SystemConstants;
import org.orange.domain.dto.RoleDto;
import org.orange.domain.dto.StatusDto;
import org.orange.domain.entity.Role;
import org.orange.domain.entity.RoleMenu;
import org.orange.domain.entity.UserRole;
import org.orange.domain.response.ResponseResult;
import org.orange.domain.vo.PageVo;
import org.orange.domain.vo.RoleVo;
import org.orange.mapper.RoleMapper;
import org.orange.mapper.RoleMenuMapper;
import org.orange.mapper.UserMapper;
import org.orange.mapper.UserRoleMapper;
import org.orange.service.RoleMenuService;
import org.orange.service.RoleService;
import org.orange.utils.BeanCopyUtils;
import org.orange.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName RoleServiceImpl
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/8
 * @Version: 1.0
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        List<String> roleKeys=new ArrayList<>();
        //判断是否是管理员，如果是管理员，集合直接返回admin
//        if(id==7L){
//            roleKeys.add("admin");
//            return roleKeys;
//        }
        //否则查询用户所具有的角色信息
        LambdaQueryWrapper<UserRole> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.eq(UserRole::getUserId,id);
        userRoleMapper.selectList(queryWrapper).forEach(userRole -> {
            Role role = roleMapper.selectById(userRole.getRoleId());
            roleKeys.add(role.getRoleKey());
        });
        return roleKeys;
    }
    //查询所有角色列表
    @Override
    public ResponseResult getRoleList(Integer pageNum, Integer pageSize, String roleName, String status) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(roleName)) {
            queryWrapper.like(Role::getRoleName, roleName);
        }
        if (!StringUtils.isEmpty(status)) {
            queryWrapper.eq(Role::getStatus, status);
        }
        queryWrapper.eq(Role::getDelFlag, SystemConstants.STATUS_NORMAL);
        queryWrapper.orderByAsc(Role::getRoleSort);
        List<Role> roleList=roleMapper.selectList(queryWrapper);
        List<RoleVo> roleVoList = BeanCopyUtils.copyBeanList(roleList, RoleVo.class);
        Page<RoleVo> page=new Page<>(pageNum,pageSize);
        page.setRecords(roleVoList);
        page.setTotal(roleList.size());
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }
    //修改角色状态
    @Override
    public ResponseResult changeStatus(StatusDto statusDto) {
        Role role = roleMapper.selectById(statusDto.getRoleId());
        role.setStatus(statusDto.getStatus());
        role.setUpdateBy(SecurityUtils.getUserId());
        role.setUpdateTime(new Date());
        roleMapper.updateById(role);
        return ResponseResult.okResult();
    }
    @Transactional
    @Override
    public ResponseResult addRole(RoleDto roleDto) {
        Role role = BeanCopyUtils.copyBean(roleDto, Role.class);
        role.setCreateBy(SecurityUtils.getUserId());
        role.setCreateTime(new Date());
        roleMapper.insert(role);
        List<Long> menuIds = roleDto.getMenuIds();
        for (Long menuId : menuIds) {
            RoleMenu  roleMenu=new RoleMenu(role.getId(),menuId);
           roleMenuMapper.insert(roleMenu);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getRole(Long id) {
        Role role = roleMapper.selectById(id);
        RoleVo roleVo=BeanCopyUtils.copyBean(role,RoleVo.class);
        return ResponseResult.okResult(roleVo);
    }

    @Override
    public ResponseResult updateRole(RoleDto roleDto) {
        Role role = BeanCopyUtils.copyBean(roleDto, Role.class);
        role.setUpdateBy(SecurityUtils.getUserId());
        role.setUpdateTime(new Date());
        roleMapper.updateById(role);
        //删除角色菜单关联信息
        LambdaQueryWrapper<RoleMenu> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenu::getRoleId,role.getId());
        roleMenuMapper.delete(queryWrapper);
        //重新插入角色菜单关联信息
        List<Long> menuIds = roleDto.getMenuIds();
        for (Long menuId : menuIds) {
            RoleMenu  roleMenu=new RoleMenu(role.getId(),menuId);
            roleMenuMapper.insert(roleMenu);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteRole(List<Long> id) {
        for (Long roleId : id) {
            Role role = roleMapper.selectById(roleId);
            role.setDelFlag(SystemConstants.DEL);
            role.setUpdateBy(SecurityUtils.getUserId());
            role.setUpdateTime(new Date());
            roleMapper.updateById(role);
            //删除角色菜单关联信息
            LambdaQueryWrapper<RoleMenu> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(RoleMenu::getRoleId,roleId);
            roleMenuMapper.delete(queryWrapper);
        }
        return ResponseResult.okResult();
    }
}
