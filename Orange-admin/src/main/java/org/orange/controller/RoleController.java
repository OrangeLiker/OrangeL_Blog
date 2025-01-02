package org.orange.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.orange.domain.dto.RoleDto;
import org.orange.domain.dto.StatusDto;
import org.orange.domain.response.ResponseResult;
import org.orange.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName RoleController
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/12
 * @Version: 1.0
 */
@RestController
@RequestMapping("/system/role")
@Api(tags = "角色管理模块",description = "角色管理模块相关接口")
public class RoleController {
    @Autowired
    private RoleService roleService;
    //查询所有角色列表
    @GetMapping("/list")
    @ApiOperation("查询角色列表")
    public ResponseResult getRoleList(Integer pageNum,Integer pageSize,String roleName,String status){
        return roleService.getRoleList(pageNum,pageSize,roleName,status);
    }
    //查询所有状态正常的角色
    @GetMapping("/listAllRole")
    @ApiOperation("查询所有角色")
    public ResponseResult listAllRole(){
        return roleService.listAllRole();
    }
    //修改角色状态
    @PutMapping("/changeStatus")
    @ApiOperation("修改角色状态")
    public ResponseResult changeStatus(@RequestBody StatusDto statusDto){
        return roleService.changeStatus(statusDto);
    }
    //新增角色
    @PostMapping
    @ApiOperation("新增角色")
    public ResponseResult addRole(@RequestBody RoleDto roleDto){
        return roleService.addRole(roleDto);
    }
    //回显角色信息
    @GetMapping("/{id}")
    @ApiOperation("回显角色")
    public ResponseResult getRole(@PathVariable Long id){
        return roleService.getRole(id);
    }
    //更新角色信息
    @PutMapping
    @ApiOperation("修改角色")
    public ResponseResult updateRole(@RequestBody RoleDto roleDto){
        return roleService.updateRole(roleDto);
    }
    //删除与批量删除
    @DeleteMapping("/{id}")
    @ApiOperation("删除角色")
    public ResponseResult deleteRole(@PathVariable List<Long> id){
        return roleService.deleteRole(id);
    }
}
