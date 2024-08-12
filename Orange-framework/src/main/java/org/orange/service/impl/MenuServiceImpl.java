package org.orange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.orange.constans.SystemConstants;
import org.orange.domain.entity.Menu;
import org.orange.domain.entity.RoleMenu;
import org.orange.domain.entity.UserRole;
import org.orange.domain.enums.AppHttpCodeEnum;
import org.orange.domain.response.ResponseResult;
import org.orange.domain.vo.MenuTreeVo;
import org.orange.domain.vo.MenuVo;
import org.orange.exception.SystemException;
import org.orange.mapper.MenuMapper;
import org.orange.mapper.RoleMenuMapper;
import org.orange.mapper.UserMapper;
import org.orange.mapper.UserRoleMapper;
import org.orange.service.MenuService;
import org.orange.utils.BeanCopyUtils;
import org.orange.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
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
        List<String> perms = new ArrayList<>();
        //管理员返回所有权限
        if (SecurityUtils.adminOrUser(id)) {//管理员
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper();
            queryWrapper.in(Menu::getMenuType, SystemConstants.MENU, SystemConstants.BUTTON);
            queryWrapper.eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);
            List<Menu> menus = list(queryWrapper);
            perms = menus.stream()
                    .map(Menu::getPerms)
                    .distinct()
                    .collect(Collectors.toList());
        } else {
            //用户返回对应权限,三表联查，根据用户id查询对应的roleid，根据roleid查询对应的menuid，根据menuid查询对应的perms
            LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserRole::getUserId, id);
            List<UserRole> userRoles = userRoleMapper.selectList(queryWrapper);
            List<Long> roleIdList = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
            LambdaQueryWrapper<RoleMenu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.in(RoleMenu::getRoleId, roleIdList);
            List<RoleMenu> roleMenus = roleMenuMapper.selectList(lambdaQueryWrapper);
            List<Long> menuIdList = roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
            LambdaQueryWrapper<Menu> menuLambdaQueryWrapper = new LambdaQueryWrapper<>();
            menuLambdaQueryWrapper.in(Menu::getId, menuIdList);
            menuLambdaQueryWrapper.in(Menu::getMenuType, SystemConstants.MENU, SystemConstants.BUTTON);
            menuLambdaQueryWrapper.eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);
            List<Menu> menus = list(menuLambdaQueryWrapper);
            perms = menus.stream()
                    .map(Menu::getPerms)
                    .distinct()
                    .collect(Collectors.toList());
        }
        return perms;
    }

    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper1 = getBaseMapper();
        List<Menu> menus = null;
        //判断管理员
        if (SecurityUtils.adminOrUser(userId)) {
            menus = menuMapper1.selectAllRoutersMenu();
        } else {
            menus = menuMapper1.selectRouterMenuTreeByUserId(userId);
        }
        //先找出第一层菜单，之后找子菜单，递归
        List<Menu> menuTree = buildMenuTree(menus, 0L);
        return menuTree;
    }

    @Override
    public ResponseResult getMenuList(String status, String menuName) {
        List<MenuVo> menuVos=new ArrayList<>();
        LambdaQueryWrapper<Menu> queryWrapper=new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(status)){
            queryWrapper.eq(Menu::getStatus,status);
        }
        if (!StringUtils.isEmpty(menuName)){
            queryWrapper.like(Menu::getMenuName,menuName);
        }
        queryWrapper.orderByAsc(Menu::getOrderNum);
        queryWrapper.eq(Menu::getDelFlag,SystemConstants.MENU_NORMAL);
        List<Menu> menus=list(queryWrapper);
        menuVos= BeanCopyUtils.copyBeanList(menus,MenuVo.class);
        return ResponseResult.okResult(menuVos);
    }

    @Override
    public ResponseResult addMenu(Menu menu) {
        if (menu.getParentId()==null){
            menu.setParentId(0L);
        }
        if (menuMapper.selectCount(new LambdaQueryWrapper<Menu>().eq(Menu::getMenuName,menu.getMenuName()))>0){
            throw new SystemException(AppHttpCodeEnum.MENU_EXISTED);
        }
        menu.setCreateBy(SecurityUtils.getUserId());
        menu.setCreateTime(new Date());
        save(menu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getMenu(Long id) {
        Menu menu = getById(id);
        MenuVo menuVo=BeanCopyUtils.copyBean(menu,MenuVo.class);
        return ResponseResult.okResult(menuVo);
    }

    @Override
    public ResponseResult updateMenu(Menu menu) {
        if (menu.getParentId()==null){
            menu.setParentId(0L);
        }
        if (menuMapper.selectCount(new LambdaQueryWrapper<Menu>().eq(Menu::getMenuName,menu.getMenuName()).ne(Menu::getId,menu.getId()))>0){
            throw new SystemException(AppHttpCodeEnum.MENU_EXISTED);
        }
        menu.setUpdateBy(SecurityUtils.getUserId());
        menu.setUpdateTime(new Date());
        updateById(menu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteMenu(Long id) {
        //先检查是否有子菜单
        if (menuMapper.selectCount(new LambdaQueryWrapper<Menu>().eq(Menu::getParentId,id))>0){
            return ResponseResult.errorResult(AppHttpCodeEnum.MENU_HAS_CHILD);
        }
        Menu menu =getById(id);
        menu.setDelFlag(SystemConstants.MENU_DELETE);
        updateById(menu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getMenuTree() {
        //先查根菜单
        LambdaQueryWrapper<Menu> queryWrapper=new LambdaQueryWrapper<>();
        List<Menu> menus=menuMapper.selectList(queryWrapper);
        //将每个menuName赋值给label
        menus=menus.stream()
                .map(menu->menu.setLabel(menu.getMenuName()))
                .collect(Collectors.toList());
        List<Menu> menuTree=buildMenuTree(menus,0L);
        return ResponseResult.okResult(menuTree);
    }
    //回显菜单树
    @Override
    public ResponseResult roleMenuTreeselect(Long id) {
        LambdaQueryWrapper<Menu> queryWrapper=new LambdaQueryWrapper<>();
        List<Menu> menus=menuMapper.selectList(queryWrapper);
        //将每个menuName赋值给label
        menus=menus.stream()
                .map(menu->menu.setLabel(menu.getMenuName()))
                .collect(Collectors.toList());
        List<Menu> menuTree=buildMenuTree(menus,0L);
        LambdaQueryWrapper<RoleMenu> queryWrapper1=new LambdaQueryWrapper<>();
        queryWrapper1.eq(RoleMenu::getRoleId,id);
        List<Long> menuIds = roleMenuMapper.selectList(queryWrapper1).stream()
                .map(roleMenu -> roleMenu.getMenuId())
                .collect(Collectors.toList());
        MenuTreeVo menuTreeVo=new MenuTreeVo(menuTree,menuIds);
        return ResponseResult.okResult(menuTreeVo);
    }


    //构建树
    private List<Menu> buildMenuTree(List<Menu> menus, Long parentId) {
        List<Menu> menuTree = menus.stream()
                                  .filter(menu -> menu.getParentId().equals(parentId))
                                  .map(menu -> menu.setChildren(getChildren(menu,menus)))
                                  .collect(Collectors.toList());
            return menuTree;
    }
    //找子菜单
    private List<Menu> getChildren(Menu menu,List<Menu> menus) {
        List<Menu> children = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m->m.setChildren(getChildren(m,menus)))//递归查找子菜单的子菜单
                .collect(Collectors.toList());
        return children;
    }
}

