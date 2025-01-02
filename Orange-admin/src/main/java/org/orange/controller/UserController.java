package org.orange.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "用户管理模块",description = "用户管理模块相关接口")
public class UserController {
    @Autowired
    private UserService userService;
    //查询用户
    @GetMapping("/list")
    @ApiOperation("查询用户列表")
    public ResponseResult getUserList(Integer pageNum,Integer pageSize,String userName,String phonenumber,String status){
        return userService.getUserList(pageNum,pageSize,userName,phonenumber,status);
    }
    //新增用户
    @PostMapping
    @ApiOperation("新增用户")
    public ResponseResult addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }
    //删除用户
    @DeleteMapping("/{id}")
    @ApiOperation("删除用户")
    public ResponseResult deleteUser(@PathVariable List<Long> id){
        return userService.deleteUser(id);
    }
    //回显用户信息
    @GetMapping("/{id}")
    @ApiOperation("回显用户")
    public ResponseResult getUser(@PathVariable Long id){
        return userService.getUser(id);
    }
    //更新用户信息
    @PutMapping
    @ApiOperation("修改用户")
    public ResponseResult updateUser(@RequestBody UserDto userDto){
        return userService.updateUser(userDto);
    }
    //更改状态
    @PutMapping("/changeStatus")
    @ApiOperation("修改用户状态")
    public ResponseResult changeStatus(@RequestBody StatusDto status){
       return userService.changeStatus(status);
    }
}
