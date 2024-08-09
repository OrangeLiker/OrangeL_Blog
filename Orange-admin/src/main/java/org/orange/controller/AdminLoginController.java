package org.orange.controller;

import org.orange.domain.entity.LoginUser;
import org.orange.domain.entity.Menu;
import org.orange.domain.entity.User;
import org.orange.domain.enums.AppHttpCodeEnum;
import org.orange.domain.response.ResponseResult;
import org.orange.domain.vo.AdminUserInfoVo;
import org.orange.domain.vo.RoutersVo;
import org.orange.domain.vo.UserInfoVo;
import org.orange.exception.SystemException;
import org.orange.service.AdminLoginService;
import org.orange.service.MenuService;
import org.orange.service.RoleService;
import org.orange.utils.BeanCopyUtils;
import org.orange.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName LoginController
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/8
 * @Version: 1.0
 */
@RestController
public class AdminLoginController {
    @Autowired
    private AdminLoginService loginService;

    @Autowired
    private  MenuService menuService;

    @Autowired
    private RoleService roleService;
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //抛出自定义异常，由异常处理统一返回
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }

    //getInfo
    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        //获取登录用户信息
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if(loginUser==null){
            throw new SystemException(AppHttpCodeEnum.NEED_LOGIN);
        }
        //根据用户id查询权限信息
        List<String> perms=menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> role=roleService.selectRoleKeyByUserId(loginUser.getUser().getId());
        //封装
       AdminUserInfoVo adminUserInfoVo=new AdminUserInfoVo(perms,role, BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class));
        return ResponseResult.okResult(adminUserInfoVo);
    }

    //getRouters
    @GetMapping("/getRouters")
    public ResponseResult<RoutersVo> getRouters(){
        Long userId = SecurityUtils.getUserId();
        //查询menu，以树形结构返回
        List<Menu> menus=menuService.selectRouterMenuTreeByUserId(userId);
        //封装返回
        return ResponseResult.okResult(new RoutersVo(menus));
    }
}
