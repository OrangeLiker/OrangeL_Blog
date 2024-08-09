package org.orange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.orange.domain.entity.Role;
import org.orange.domain.entity.UserRole;
import org.orange.mapper.RoleMapper;
import org.orange.mapper.UserMapper;
import org.orange.mapper.UserRoleMapper;
import org.orange.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        String type=userMapper.selectById(id).getType();
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
    public Boolean adminOrUser(String type){
        return type.equals("1");
    }
}
