package org.orange.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.orange.domain.entity.Menu;
import org.orange.domain.response.ResponseResult;
import org.orange.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName MenuController
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/12
 * @Version: 1.0
 */
@RestController
@RequestMapping("/system/menu")
@Api(tags = "菜单管理模块",description = "菜单管理模块相关接口")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @GetMapping("/list")
    @ApiOperation("查询菜单列表")
    public ResponseResult getMenuList(String status,String menuName){
        return menuService.getMenuList(status,menuName);
    }
    //新增菜单
    @PostMapping
    @ApiOperation("新增菜单")
    public ResponseResult addMenu(@RequestBody Menu menu){
        return menuService.addMenu(menu);
    }
    //修改菜单
    @GetMapping("/{id}")
    @ApiOperation("修改菜单")
    public ResponseResult getMenu(@PathVariable Long id){
        return menuService.getMenu(id);
    }
    @PutMapping()
    @ApiOperation("修改菜单")
    public ResponseResult updateMenu(@RequestBody Menu menu){
        return menuService.updateMenu(menu);
    }
    //删除菜单
    @DeleteMapping("/{id}")
    @ApiOperation("删除菜单")
    public ResponseResult deleteMenu(@PathVariable Long id){
        return menuService.deleteMenu(id);
    }
    //获取菜单树
    @GetMapping("/treeselect")
    @ApiOperation("获取菜单树")
    public ResponseResult getMenuTree(){
        return menuService.getMenuTree();
    }
    //回显菜单树
    @GetMapping("/roleMenuTreeselect/{id}")
    @ApiOperation("回显菜单树")
    public ResponseResult roleMenuTreeselect(@PathVariable Long id){
        return menuService.roleMenuTreeselect(id);
    }
}
