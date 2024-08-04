package org.orange.controller;

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
public class ArticleController {
    @Autowired
    private ArticleService articleService;

//    @GetMapping("/list")
//    public List<Article> list(){
//        return articleService.list();
//    }
    //热门文章查询
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){
        ResponseResult result=articleService.hotArticleList();
        return result;
    }
    //分页查询
    @GetMapping("/articleList")
    public ResponseResult articleList(Long categoryId,Long pageNum,Long pageSize){
        return articleService.articleList(categoryId,pageNum,pageSize);
    }
}
