package org.orange.controller;

import org.orange.domain.dto.ArticleDto;
import org.orange.domain.enums.AppHttpCodeEnum;
import org.orange.domain.response.ResponseResult;
import org.orange.domain.vo.CategoryVo;
import org.orange.domain.vo.UpdateArticleVo;
import org.orange.mapper.CategoryMapper;
import org.orange.service.ArticleService;
import org.orange.service.CategoryService;
import org.orange.service.TagService;
import org.orange.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName ArticleController
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/11
 * @Version: 1.0
 */
@RestController
public class ArticleController {

    @Autowired
    private UploadService uploadService;
    @Autowired
    private ArticleService articleService;

    //上传图片
    @PostMapping("/upload")
    public ResponseResult uploadFile(@RequestParam("img") MultipartFile file) throws IOException {
        if(file==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.FILE_NOTEMPTY,"文件不能为空");
        }
        return uploadService.uploadImage(file);
    }
    //新增博文
    @PostMapping("/content/article")
    public ResponseResult addArticle(@RequestBody ArticleDto articleDto){
        return articleService.addArticle(articleDto);
    }

    //查询文章
    @GetMapping("content/article/list")
    public ResponseResult getArticleList(Integer pageNum, Integer pageSize,ArticleDto articleDto){
        return articleService.getArticleList(pageNum,pageSize,articleDto);
    }
    //获取修改文章信息
    @GetMapping("content/article/{id}")
    public ResponseResult getUpdateArticle(@PathVariable("id") Long id){
        return articleService.getUpdateArticle(id);
    }
    //更新文章
    @PutMapping("content/article")
    public ResponseResult updateArticle(@RequestBody UpdateArticleVo updateArticleVo){
        return articleService.updateArticle(updateArticleVo);
    }
}
