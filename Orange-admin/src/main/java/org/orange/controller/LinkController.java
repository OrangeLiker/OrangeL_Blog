package org.orange.controller;

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
public class LinkController {
    @Autowired
    private LinkService linkService;
    //查询全部友链
    @GetMapping("/list")
    public ResponseResult getAlllinks(Integer pageNum,Integer pageSize,String name,String status){
        return linkService.getAllLinks(pageNum,pageSize,name,status);
    }
    //新增友链
    @PostMapping
    public ResponseResult addLink(@RequestBody LinkDto linkDto){
        return linkService.addLink(linkDto);
    }
    //修改友链-先回显
    @GetMapping("/{id}")
    public ResponseResult getLink(@PathVariable("id") Long id){
        return linkService.getLink(id);
    }
    //修改
    @PutMapping
    public ResponseResult updateLink(@RequestBody LinkDto linkDto){
        return linkService.updateLink(linkDto);
    }
    //删除
    @DeleteMapping("/{id}")
    public ResponseResult deleteLink(@PathVariable("id") List<Long> id){
        return linkService.deleteLink(id);
    }
}
