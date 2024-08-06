package org.orange.controller;

import org.orange.constans.SystemConstants;
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
        return commentService.getCommentList(SystemConstants.COMMENT_TYPE_ARTICLE,articleId,pageNum,pageSize);
    }
    //发布文章评论
    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment){//前端传过来的comment，正常来说是需要新建一个DTO用来接收的
        return commentService.addComment(comment);
    }

    //友链评论
    @GetMapping("/linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        return commentService.getCommentList(SystemConstants.COMMENT_TYPE_LINK,null,pageNum,pageSize);
    }
}
