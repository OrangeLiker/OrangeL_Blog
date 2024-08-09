package org.orange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.orange.constans.SystemConstants;
import org.orange.domain.entity.Menu;
import org.orange.domain.entity.RoleMenu;
import org.orange.domain.entity.User;
import org.orange.domain.entity.UserRole;
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
        System.out.println(type);
        //管理员返回所有权限
         if(id==7L){//管理员
             LambdaQueryWrapper<Menu> queryWrapper=new LambdaQueryWrapper();
             queryWrapper.in(Menu::getMenuType, SystemConstants.MENU, SystemConstants.BUTTON);
             queryWrapper.eq(Menu::getStatus,SystemConstants.STATUS_NORMAL);
             List<Menu> menus = list(queryWrapper);
             List <String> perms=menus.stream().map(Menu::getPerms).collect(Collectors.toList());
             return perms;
         }
           //用户返回对应权限,三表联查，根据用户id查询对应的roleid，根据roleid查询对应的menuid，根据menuid查询对应的perms
             Long roleId = userRoleMapper.selectById(id).getRoleId();
             if(roleId==null){
                 throw new RuntimeException("用户未分配角色");
             }
             LambdaQueryWrapper<RoleMenu> queryWrapper=new LambdaQueryWrapper<>();
             queryWrapper.eq(RoleMenu::getRoleId,roleId);
             List<RoleMenu> roleMenus = roleMenuMapper.selectList(queryWrapper);
             List<String> perms=new ArrayList<>();
             for (RoleMenu roleMenu : roleMenus) {
                 LambdaQueryWrapper<Menu> queryWrapper1 = new LambdaQueryWrapper<>();
                 queryWrapper1.eq(Menu::getId, roleMenu.getMenuId());
                 queryWrapper1.eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);
                 List<Menu> menus = menuMapper.selectList(queryWrapper1);
                 for (Menu menu : menus) {
                     perms.add(menu.getPerms());
                 }
             }
             return perms;
    }
    //判断权限
    public Boolean adminOrUser(String type){

        return type.equals("1");
    }
}
