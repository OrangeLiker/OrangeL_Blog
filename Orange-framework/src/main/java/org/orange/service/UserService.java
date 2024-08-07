package org.orange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.orange.domain.entity.User;
import org.orange.domain.response.ResponseResult;

public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);
}
