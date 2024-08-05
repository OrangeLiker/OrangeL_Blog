package org.orange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.orange.domain.entity.LoginUser;
import org.orange.domain.entity.User;
import org.orange.mapper.UserMapper;
import org.orange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName UserDetailsImpl
 * @Description UserDetailsService实现类.实现本地数据库查询
 * @Author WangZJ0908
 * @Date 2024/8/5
 * @Version: 1.0
 */
@Service
public class UserDetailsImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,username);
        User user=userMapper.selectOne(wrapper);
        //判断
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("用户名不存在");
        }
        //TODO 查询用户权限

        //用封装的LoginUser接收返回
        return new LoginUser(user);
    }
}
