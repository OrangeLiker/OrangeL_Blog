package org.orange.controller;

import io.swagger.annotations.ApiOperation;
import org.orange.annotation.SystemLog;
import org.orange.domain.response.ResponseResult;
import org.orange.service.AdminLogoutService;
import org.orange.service.BlogLogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName AdminLogoutController
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/9
 * @Version: 1.0
 */
@RestController
public class AdminLogoutController {
    @Autowired
    private AdminLogoutService logoutService;
    @PostMapping("/user/logout")
    @SystemLog(businessName = "退出登录")
    @ApiOperation("退出登录")
    public ResponseResult logout(){
        return logoutService.logout();
    }

}
