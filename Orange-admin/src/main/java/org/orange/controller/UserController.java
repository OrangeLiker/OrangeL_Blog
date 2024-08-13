package org.orange.controller;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import org.orange.domain.dto.StatusDto;
import org.orange.domain.dto.UserDto;
import org.orange.domain.entity.User;
import org.orange.domain.response.ResponseResult;
import org.orange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName UserController
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/12
 * @Version: 1.0
 */
@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private UserService userService;
    //查询用户
    @GetMapping("/list")
    public ResponseResult getUserList(Integer pageNum,Integer pageSize,String userName,String phonenumber,String status){
        return userService.getUserList(pageNum,pageSize,userName,phonenumber,status);
    }
    //新增用户
    @PostMapping
    public ResponseResult addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }
    //删除用户
    @DeleteMapping("/{id}")
    public ResponseResult deleteUser(@PathVariable List<Long> id){
        return userService.deleteUser(id);
    }
    //回显用户信息
    @GetMapping("/{id}")
    public ResponseResult getUser(@PathVariable Long id){
        return userService.getUser(id);
    }
    //更新用户信息
    @PutMapping
    public ResponseResult updateUser(@RequestBody UserDto userDto){
        return userService.updateUser(userDto);
    }
    //更改状态
    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody StatusDto status){
       return userService.changeStatus(status);
    }
}
