package org.orange.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.orange.annotation.SystemLog;
import org.orange.domain.entity.User;
import org.orange.domain.enums.AppHttpCodeEnum;
import org.orange.domain.response.ResponseResult;
import org.orange.exception.SystemException;
import org.orange.service.UserService;
import org.orange.utils.EmailService;
import org.orange.utils.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName UserController
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/6
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
@Validated
@Api(tags = "用户模块",description = "用户模块相关接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @GetMapping("/userInfo")
    @SystemLog(businessName = "查询用户信息")
    @ApiOperation("查询用户信息")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    @SystemLog(businessName = "更新用户信息")
    @ApiOperation("更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }
    @PostMapping("/register")
    @SystemLog(businessName = "用户注册")
    @ApiOperation("用户注册")
    public ResponseResult register(@Validated @RequestBody User user){
        return userService.register(user);
    }
    @PostMapping("/askCode")
    @SystemLog(businessName = "获取验证码")
    @ApiOperation("获取验证码")
    public ResponseResult askCode(@RequestParam String email, @RequestParam String type) throws MessagingException, UnsupportedEncodingException {
        return emailService.sendVerificationEmail(email, type);
    }
}

