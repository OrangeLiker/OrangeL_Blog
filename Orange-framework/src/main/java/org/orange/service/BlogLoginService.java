package org.orange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.orange.domain.entity.User;
import org.orange.domain.response.ResponseResult;

public interface BlogLoginService {
    ResponseResult login(User user);
}
