package org.orange.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
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
@Api(tags = "文章管理模块",description = "文章管理模块相关接口")
@Validated
public class ArticleController {

    @Autowired
    private UploadService uploadService;
    @Autowired
    private ArticleService articleService;

    //上传图片
    @PostMapping("/upload")
    @ApiOperation("上传图片")
    public ResponseResult uploadFile(@RequestParam("img") MultipartFile file) throws IOException {
        if(file==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.FILE_NOTEMPTY,"文件不能为空");
        }
        return uploadService.uploadImage(file);
    }
    //新增博文
    @PostMapping("/content/article")
    @ApiOperation("新增文章")
    public ResponseResult addArticle(@NotNull @RequestBody ArticleDto articleDto){
        return articleService.addArticle(articleDto);
    }

    //查询文章
    @GetMapping("content/article/list")
    @ApiOperation("查询文章")
    public ResponseResult getArticleList(Integer pageNum, Integer pageSize,ArticleDto articleDto){
        return articleService.getArticleList(pageNum,pageSize,articleDto);
    }
    //获取修改文章信息
    @GetMapping("content/article/{id}")
    @ApiOperation("获取修改文章信息")
    public ResponseResult getUpdateArticle(@PathVariable("id") Long id){
        return articleService.getUpdateArticle(id);
    }
    //更新文章
    @PutMapping("content/article")
    @ApiOperation("更新文章")
    public ResponseResult updateArticle(@NotNull @RequestBody UpdateArticleVo updateArticleVo){
        return articleService.updateArticle(updateArticleVo);
    }
    //删除文章，支持批量删除，同时要对tags表进行删除
    @DeleteMapping("content/article/{id}")
    @ApiOperation("删除文章")
    public ResponseResult deleteArticle(@PathVariable("id") List<Long> id){
        return articleService.deleteArticle(id);
    }
}
