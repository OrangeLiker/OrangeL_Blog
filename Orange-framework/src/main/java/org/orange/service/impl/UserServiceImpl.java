package org.orange.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.orange.domain.entity.User;
import org.orange.mapper.UserMapper;
import org.orange.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/5
 * @Version: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
