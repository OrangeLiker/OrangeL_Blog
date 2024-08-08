package org.orange.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.orange.annotation.SystemLog;
import org.orange.domain.entity.Category;
import org.orange.domain.response.ResponseResult;
import org.orange.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName CategoryController
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/4
 * @Version: 1.0
 */
@RestController
@RequestMapping("/category")
@Api(tags = "文章分类分类模块",description = "文章分类分类模块相关接口")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    //查询条件：只展示有正式文章发布的分类，必须是正常状态的分类，分表查询
    @GetMapping("/getCategoryList")
    @SystemLog(businessName = "查询分类列表")
    @ApiOperation(value = "查询分类列表")
    public ResponseResult list(){
         return categoryService.getCategoryList();
    }
}
