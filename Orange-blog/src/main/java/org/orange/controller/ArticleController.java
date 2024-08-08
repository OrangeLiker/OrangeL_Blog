package org.orange.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.orange.annotation.SystemLog;
import org.orange.domain.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.orange.service.ArticleService;
import org.orange.domain.response.ResponseResult;
import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName ArticleController
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/4
 * @Version: 1.0
 */
@RestController
@RequestMapping("/article")
@Api(tags = "文章模块",description = "文章模块相关接口")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    //热门文章查询
    @GetMapping("/hotArticleList")
    @SystemLog(businessName = "查询热门文章")
    @ApiOperation(value = "查询热门文章")
    public ResponseResult hotArticleList(){
        ResponseResult result=articleService.hotArticleList();
        return result;
    }
    //分页查询
    @GetMapping("/articleList")
    @SystemLog(businessName = "查询文章列表")
    @ApiOperation(value = "查询文章列表")
    public ResponseResult articleList(Long categoryId,Long pageNum,Long pageSize){
        return articleService.articleList(categoryId,pageNum,pageSize);
    }

    //文章详情
    @GetMapping("/{id}")
    @SystemLog(businessName = "查询文章详情")
    @ApiOperation(value = "查询文章详情")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }

    //更新浏览量
    @PutMapping("/updateViewCount/{id}")
    @ApiOperation(value = "更新浏览量")
    public ResponseResult updateViewCount(@PathVariable("id") Long id){
        return articleService.updateViewCount(id);
    }
}
