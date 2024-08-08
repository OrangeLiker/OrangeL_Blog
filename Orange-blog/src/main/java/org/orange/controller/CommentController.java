package org.orange.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.orange.annotation.SystemLog;
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
@Api(tags = "评论模块",description = "评论模块相关接口")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @GetMapping("/commentList")
    @SystemLog(businessName = "查询文章评论")
    @ApiOperation("查询文章评论")
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.getCommentList(SystemConstants.COMMENT_TYPE_ARTICLE,articleId,pageNum,pageSize);
    }
    //发布文章评论
    @PostMapping
    @SystemLog(businessName = "发布文章评论")
    @ApiOperation("发布文章评论")
    public ResponseResult addComment(@RequestBody Comment comment){//前端传过来的comment，正常来说是需要新建一个DTO用来接收的
        return commentService.addComment(comment);
    }

    //友链评论
    @GetMapping("/linkCommentList")
    @SystemLog(businessName = "查询友链评论")
    @ApiOperation("查询友链评论")
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        return commentService.getCommentList(SystemConstants.COMMENT_TYPE_LINK,null,pageNum,pageSize);
    }
}
