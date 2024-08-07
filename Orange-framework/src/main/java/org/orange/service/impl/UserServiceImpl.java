package org.orange.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.orange.domain.entity.User;
import org.orange.domain.response.ResponseResult;
import org.orange.domain.vo.UserInfoVo;
import org.orange.mapper.UserMapper;
import org.orange.service.UserService;
import org.orange.utils.BeanCopyUtils;
import org.orange.utils.SecurityUtils;
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
    @Override
    public ResponseResult userInfo() {
        Long userId = SecurityUtils.getUserId();
        if(userId == null){
            throw new RuntimeException("用户未登录");
        }
        User user = getById(userId);
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }
    //修改用户信息,简单修改
    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }
}
