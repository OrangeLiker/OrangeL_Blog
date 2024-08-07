package org.orange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.orange.constans.SystemConstants;
import org.orange.domain.entity.Comment;
import org.orange.domain.enums.AppHttpCodeEnum;
import org.orange.domain.response.ResponseResult;
import org.orange.domain.vo.CommentVo;
import org.orange.domain.vo.PageVo;
import org.orange.exception.SystemException;
import org.orange.mapper.CommentMapper;
import org.orange.service.CommentService;
import org.orange.service.UserService;
import org.orange.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName CommentServiceImpl
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/6
 * @Version: 1.0
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Override
    public ResponseResult getCommentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //查询对应文章的根评论
        LambdaQueryWrapper<Comment> queryWrapper=new LambdaQueryWrapper();//只有在后边加上对应泛型，才能在eq方法中传入对应的字段
        //只有当commentType=0时，才会有articleId
        queryWrapper.eq(SystemConstants.COMMENT_TYPE_ARTICLE.equals(commentType),Comment::getArticleId,articleId);
        //根评论id=-1
        queryWrapper.eq(Comment::getRootId, SystemConstants.ROOT_COMMENT);
        //评论类型判断
        queryWrapper.eq(Comment::getType,commentType);
        //分页查询
        Page<Comment> page=new Page(pageNum,pageSize);
        page(page,queryWrapper);

        //封装成PageVo
        List<CommentVo> commentVoList= toCommentVoList(page.getRecords());
        //查询所有根评论的子评论赋值给children
        for (CommentVo commentVo : commentVoList) {
            commentVo.setChildren(getChildren(commentVo.getId()));
        }
        PageVo pageVo=new PageVo(commentVoList,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }
    //添加评论
    @Override
    public ResponseResult addComment(Comment comment) {
        //评论内容不能为空
        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOTNULL);
        }
        save(comment);//保存评论,前提是已经通过SecurityUtils中的方法结合注解填充了缺失字段
        return ResponseResult.okResult();
    }

    //封装成方法
    private List<CommentVo> toCommentVoList(List<Comment> list){
       List<CommentVo> commentVoList=BeanCopyUtils.copyBeanList(list,CommentVo.class);
       //遍历Vo集合，补充遗漏字段
        for(CommentVo commentVo:commentVoList){
            //通过createBy查询用户昵称并赋值
            String nikeName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nikeName);
            //通过toCommentUserId查询用户昵称并赋值,不是所有评论都有toCommentUserId
            if(commentVo.getToCommentId()!=-1){
                commentVo.setToCommentUserName(userService.getById(commentVo.getToCommentUserId()).getNickName());
            }
        }
        return commentVoList;
    }

    //查询子评论方法
    private  List<CommentVo> getChildren(Long rootId){
        LambdaQueryWrapper<Comment> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.eq(Comment::getRootId,rootId);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> children=list(queryWrapper);
        return toCommentVoList(children);
    }
}
