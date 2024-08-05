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
    public static final int ARTICLE_STATUS_DRAFT = 1;
    //文章是发布
    public static final int ARTICLE_STATUS_NORMAL = 0;
    //分类状态正常
    public static final String Category_STATUS_NORMAL = "0";
    //分类状态删除
    public static final String Category_STATUS_DELETE = "1";
    //友情链接审核通过
    public static final char LINK_STATUS_PASS = '0';
    //友情链接审核未通过

}
