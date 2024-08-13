package org.orange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.orange.domain.dto.StatusDto;
import org.orange.domain.dto.UserDto;
import org.orange.domain.entity.User;
import org.orange.domain.response.ResponseResult;

import java.util.List;

public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);

    ResponseResult getUserList(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status);

    ResponseResult addUser(UserDto userDto);

    ResponseResult deleteUser(List<Long> id);

    ResponseResult getUser(Long id);

    ResponseResult updateUser(UserDto userDto);

    ResponseResult changeStatus(StatusDto status);
}
