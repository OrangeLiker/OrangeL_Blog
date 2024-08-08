package org.orange.controller;

import org.orange.domain.entity.Tag;
import org.orange.domain.response.ResponseResult;
import org.orange.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult list(){
        List<Tag> list = tagService.list();
        return ResponseResult.okResult(list);
    }

}
