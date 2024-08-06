package org.orange.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName CommentVo
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/6
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    private Long id;


    /**
     * 文章id
     */

    private Long articleId;

    /**
     * 根评论id
     */

    private Long rootId;

    /**
     * 评论内容
     */

    private String content;

    /**
     * 所回复的目标评论的userid
     */

    private Long toCommentUserId;

    /**
     * 回复目标评论id
     */

    private Long toCommentId;

    private String toCommentUserName;

    private String username;

    private Long createBy;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    //子评论
    private List<CommentVo> children;

}
