package org.orange.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.orange.domain.dto.LinkDto;
import org.orange.domain.response.ResponseResult;
import org.orange.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName LinkController
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/13
 * @Version: 1.0
 */
@RestController
@RequestMapping("/content/link")
@Api(tags = "友链管理模块",description = "友链管理模块相关接口")
public class LinkController {
    @Autowired
    private LinkService linkService;
    //查询全部友链
    @GetMapping("/list")
    @ApiOperation("查询友链列表")
    public ResponseResult getAlllinks(Integer pageNum,Integer pageSize,String name,String status){
        return linkService.getAllLinks(pageNum,pageSize,name,status);
    }
    //新增友链
    @PostMapping
    @ApiOperation("新增友链")
    public ResponseResult addLink(@RequestBody LinkDto linkDto){
        return linkService.addLink(linkDto);
    }
    //修改友链-先回显
    @GetMapping("/{id}")
    @ApiOperation("修改友链")
    public ResponseResult getLink(@PathVariable("id") Long id){
        return linkService.getLink(id);
    }
    //修改
    @PutMapping
    @ApiOperation("修改友链状态")
    public ResponseResult updateLink(@RequestBody LinkDto linkDto){
        return linkService.updateLink(linkDto);
    }
    //删除
    @DeleteMapping("/{id}")
    @ApiOperation("删除友链")
    public ResponseResult deleteLink(@PathVariable("id") List<Long> id){
        return linkService.deleteLink(id);
    }
}
