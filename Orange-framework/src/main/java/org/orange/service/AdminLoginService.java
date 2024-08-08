package org.orange.service;

import org.orange.domain.entity.User;
import org.orange.domain.response.ResponseResult;

public interface AdminLoginService{
    ResponseResult login(User user);
}
