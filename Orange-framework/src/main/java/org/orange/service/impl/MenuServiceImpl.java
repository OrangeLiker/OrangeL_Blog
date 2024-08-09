package org.orange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.orange.constans.SystemConstants;
import org.orange.domain.entity.Menu;
import org.orange.domain.entity.RoleMenu;
import org.orange.domain.entity.User;
import org.orange.domain.entity.UserRole;
import org.orange.domain.enums.AppHttpCodeEnum;
import org.orange.exception.SystemException;
import org.orange.mapper.MenuMapper;
import org.orange.mapper.RoleMenuMapper;
import org.orange.mapper.UserMapper;
import org.orange.mapper.UserRoleMapper;
import org.orange.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName MenuServiceImpl
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/8
 * @Version: 1.0
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Override
    public List<String> selectPermsByUserId(Long id) {
        String type = userMapper.selectById(id).getType();
        List<String> perms=new ArrayList<>();
        //管理员返回所有权限
         if(adminOrUser(type)){//管理员
             LambdaQueryWrapper<Menu> queryWrapper=new LambdaQueryWrapper();
             queryWrapper.in(Menu::getMenuType, SystemConstants.MENU, SystemConstants.BUTTON);
             queryWrapper.eq(Menu::getStatus,SystemConstants.STATUS_NORMAL);
             List<Menu> menus = list(queryWrapper);
             perms=menus.stream().map(Menu::getPerms).collect(Collectors.toList());
         }else{
             //用户返回对应权限,三表联查，根据用户id查询对应的roleid，根据roleid查询对应的menuid，根据menuid查询对应的perms
             LambdaQueryWrapper<UserRole> queryWrapper=new LambdaQueryWrapper<>();
             queryWrapper.eq(UserRole::getUserId,id);
             List<UserRole> userRoles=userRoleMapper.selectList(queryWrapper);
             List<Long> roleIdList=userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
             LambdaQueryWrapper<RoleMenu> lambdaQueryWrapper=new LambdaQueryWrapper<>();
             lambdaQueryWrapper.in(RoleMenu::getRoleId,roleIdList);
             List<RoleMenu> roleMenus=roleMenuMapper.selectList(lambdaQueryWrapper);
             List<Long> menuIdList=roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
             LambdaQueryWrapper<Menu> menuLambdaQueryWrapper=new LambdaQueryWrapper<>();
             menuLambdaQueryWrapper.in(Menu::getId,menuIdList);
             menuLambdaQueryWrapper.in(Menu::getMenuType,SystemConstants.MENU,SystemConstants.BUTTON);
             menuLambdaQueryWrapper.eq(Menu::getStatus,SystemConstants.STATUS_NORMAL);
             List<Menu> menus = list(menuLambdaQueryWrapper);
             perms=menus.stream().map(Menu::getPerms).collect(Collectors.toList());
         }
         return perms;
    }
    //判断权限
    public Boolean adminOrUser(String type){
        return type.equals("1");
    }
}
