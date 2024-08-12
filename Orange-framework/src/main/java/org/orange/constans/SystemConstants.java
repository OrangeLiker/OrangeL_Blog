package org.orange.constans;

import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName SystemConstans
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/4
 * @Version: 1.0
 */
@Configuration
public class SystemConstants {
    //文章是草稿
    public static final String ARTICLE_STATUS_DRAFT = "1";
    //文章是发布
    public static final String ARTICLE_STATUS_NORMAL = "0";
    public static final int ARTICLE_STATUS_DELETE = 1;
    //分类状态正常
    public static final String Category_STATUS_NORMAL = "0";
    //分类状态正常
    public static final int Category_STATUS_NOTDELETE = 0;
    public static final int Category_STATUS_DELETE = 1;
    //友情链接审核通过
    public static final char LINK_STATUS_PASS = '0';
    //根评论
    public static final Long ROOT_COMMENT = -1L;
    //文章评论
    public static final String COMMENT_TYPE_ARTICLE = "0";
    //友链评论
    public static final String COMMENT_TYPE_LINK = "1";
    //articleRedisKey
    public static final String ARTICLE_VIEW_COUNT = "ArticleViewCount";
    public static final String MENU="C";
    public static final String BUTTON="F";
    public static final String STATUS_NORMAL="0";

    public static final String MENU_DELETE="1";
    public static final String MENU_NORMAL="0";
    public static final String DEL="1";
    public static final String NORMAL="0";

}
