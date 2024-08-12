package org.orange.service.impl;

import org.orange.domain.entity.LoginUser;
import org.orange.domain.entity.User;
import org.orange.domain.response.ResponseResult;
import org.orange.domain.vo.LoginVo;
import org.orange.domain.vo.UserInfoVo;
import org.orange.service.AdminLoginService;
import org.orange.utils.BeanCopyUtils;
import org.orange.utils.JwtUtil;
import org.orange.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName AdminLoginServiceImpl
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/8
 * @Version: 1.0
 */
@Service
public class AdminLoginServiceImpl implements AdminLoginService {
    @Autowired
    private AuthenticationManager authenticationManager;//认证管理器,需要在配置中新建暴露出来

    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult login(User user) {
        System.out.println(user.getUserName()+" "+user.getPassword());
        //创建一个认证对象
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        //认证,实际上会调用UserDetailsService的认证方法，但是要从数据库查询，所以要实现UserDetailsService接口
        Authentication authenticate = authenticationManager.authenticate(token);//接收返回的认证对象
        System.out.println(authenticate.getClass());
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误！");
        }
        //获取认证对象id，生成token
        LoginUser loginUser=(LoginUser) authenticate.getPrincipal();
        String userId=loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //将用户信息传入redis
        redisCache.setCacheObject("adminlogin:"+userId,loginUser);
        //把token，userinfo封装返回
        Map<String,String> map=new HashMap<>();
        map.put("token",jwt);
        return ResponseResult.okResult(map);
    }
}
