package org.orange.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.orange.annotation.SystemLog;
import org.orange.domain.entity.User;
import org.orange.domain.enums.AppHttpCodeEnum;
import org.orange.domain.response.ResponseResult;
import org.orange.exception.SystemException;
import org.orange.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName BlogLoginController
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/5
 * @Version: 1.0
 */
@RestController
@Api(tags = "登录模块",description = "登录模块相关接口")
public class BlogLoginController {
    @Autowired
    private BlogLoginService loginService;

    @PostMapping("/login")
    @SystemLog(businessName = "用户登录")
    @ApiOperation("用户登录")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //抛出自定义异常，由异常处理统一返回
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }

}
