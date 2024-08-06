package org.orange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.orange.domain.entity.Comment;
import org.orange.domain.response.ResponseResult;
import org.orange.mapper.CommentMapper;

public interface CommentService extends IService<Comment>  {
    ResponseResult getCommentList(Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}
