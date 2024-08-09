package org.orange.service.impl;

import org.orange.domain.entity.LoginUser;
import org.orange.domain.response.ResponseResult;
import org.orange.service.AdminLogoutService;
import org.orange.utils.RedisCache;
import org.orange.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName AdminLogoutServiceImpl
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/9
 * @Version: 1.0
 */
@Service
public class AdminLogoutServiceImpl implements AdminLogoutService {
    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult logout() {
        Long userId = SecurityUtils.getUserId();
        redisCache.deleteObject("adminlogin:"+userId);
        return ResponseResult.okResult();
    }
}
