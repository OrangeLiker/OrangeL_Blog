package org.orange.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName ArticleDto
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/11
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    /**
     * 标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 所属分类id
     */
    private Long categoryId;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 是否置顶（0否，1是）
     */
    private String isTop;

    /**
     * 状态（0已发布，1草稿）
     */
    private String status;


    /**
     * 是否允许评论 1是，0否
     */
    private String isComment;

    private List<Long> tags;
}
