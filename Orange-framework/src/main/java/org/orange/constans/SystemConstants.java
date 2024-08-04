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
}
