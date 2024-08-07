package org.orange.controller;

import org.orange.annotation.SystemLog;
import org.orange.domain.response.ResponseResult;
import org.orange.service.BlogLogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName BlogLogoutController
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/6
 * @Version: 1.0
 */
@RestController
public class BlogLogoutController {
    @Autowired
    private BlogLogoutService logoutService;
    @PostMapping("/logout")
    @SystemLog(businessName = "退出登录")
    public ResponseResult logout(){
        return logoutService.logout();
    }
}
