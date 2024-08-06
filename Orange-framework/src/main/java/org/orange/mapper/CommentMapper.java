package org.orange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.orange.domain.entity.Comment;
/**
 * @BelongsProject: Orange_Blog
 * @ClassName CommentMapper
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/6
 * @Version: 1.0
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
