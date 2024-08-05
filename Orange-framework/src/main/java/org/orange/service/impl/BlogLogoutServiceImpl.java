package org.orange.service.impl;

import org.orange.domain.entity.LoginUser;
import org.orange.domain.response.ResponseResult;
import org.orange.service.BlogLogoutService;
import org.orange.utils.JwtUtil;
import org.orange.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName BlogLogoutServiceImpl
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/6
 * @Version: 1.0
 */
@Service
public class BlogLogoutServiceImpl implements BlogLogoutService {
    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult logout() {
        //删除redis中的用户信息,首先要解析token，获取userid，根据拼接userid得出key来删除信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        redisCache.deleteObject("bloglogin:"+userId);
        return ResponseResult.okResult();
    }
}
