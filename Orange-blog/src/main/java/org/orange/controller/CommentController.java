package org.orange.controller;

import org.orange.domain.entity.Comment;
import org.orange.domain.response.ResponseResult;
import org.orange.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName CommentController
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/6
 * @Version: 1.0
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.getCommentList(articleId,pageNum,pageSize);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment){//前端传过来的comment，正常来说是需要新建一个DTO用来接收的
        return commentService.addComment(comment);
    }
}
