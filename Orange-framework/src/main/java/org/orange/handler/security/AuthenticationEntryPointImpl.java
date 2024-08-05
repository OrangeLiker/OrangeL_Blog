package org.orange.handler.security;

import com.alibaba.fastjson.JSON;
import org.orange.domain.enums.AppHttpCodeEnum;
import org.orange.domain.response.ResponseResult;
import org.orange.utils.WebUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName AuthenticationEntryPointImpl
 * @Description 认证失败处理类
 * @Author WangZJ0908
 * @Date 2024/8/5
 * @Version: 1.0
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        e.printStackTrace();
        //根据具体的异常类型,返回不同的提示信息
        ResponseResult result=null;
        if(e instanceof BadCredentialsException){
            //密码错误
            result=ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR.getCode(),e.getMessage());
        }else if(e instanceof InsufficientAuthenticationException){
            //权限不足
            result=ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }else{
            //其他异常
            result=ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),"认证或授权失败！");
        }
        //将result转换为json字符串,渲染回前端
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
