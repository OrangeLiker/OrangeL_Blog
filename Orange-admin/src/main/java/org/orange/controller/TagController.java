package org.orange.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.orange.domain.dto.TagListDto;
import org.orange.domain.entity.Tag;
import org.orange.domain.response.ResponseResult;
import org.orange.domain.vo.PageVo;
import org.orange.domain.vo.TagVo;
import org.orange.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName TagController
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/8
 * @Version: 1.0
 */
@RestController
@RequestMapping("/content/tag")
@Api(tags = "标签管理模块",description = "标签管理模块相关接口")
public class TagController {

    @Autowired
    private TagService tagService;
    @GetMapping("/list")
    @ApiOperation("查询标签列表")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto) {

        return tagService.getTagList(pageNum, pageSize, tagListDto);
    }
    //查询所有标签
    @GetMapping("/listAllTag")
    @ApiOperation("查询所有标签")
    public ResponseResult listAllTag(){
        return tagService.getAllTag();
    }
    //新增标签
    @PostMapping
    @ApiOperation("新增标签")
    public ResponseResult addTag(@RequestBody TagListDto tagListDto){
        return tagService.addTag(tagListDto);
    }

    //删除标签（List接收实现批量删除）
     @DeleteMapping("/{id}")
    @ApiOperation("删除标签")
    public ResponseResult deleteTag(@PathVariable("id")List<Long> id){
        return tagService.deleteTag(id);
    }

    //1.获取标签信息
    @GetMapping("/{id}")
    @ApiOperation("获取标签信息")
    public ResponseResult getTag(@PathVariable("id")Long id){
        return tagService.getTag(id);
    }
    //2.修改
    @PutMapping
    @ApiOperation("修改标签")
    public ResponseResult updateTag(@RequestBody TagVo tagVo){
        return tagService.updateTag(tagVo);
    }
}
