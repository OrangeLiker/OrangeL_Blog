package org.orange.domain.entity;

import java.util.Date;
import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 文章表
 * @author makejava
 * @date 2024-08-04 19:51:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sg_article")
@Accessors(chain = true)//链式调用
public class Article {
    @TableId
    @JSONField(serializeUsing= ToStringSerializer.class)
    private Long id;

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

    @TableField(exist = false)//表示不是数据库字段
    private String categoryName;
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
     * 访问量
     */     
    private Long viewCount;

    /**
     * 是否允许评论 1是，0否
     */     
    private String isComment;


    private Long createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */     
    private Integer delFlag;


    public Article(Long id, long viewCount) {
        this.id=id;
        this.viewCount=viewCount;

    }
}

