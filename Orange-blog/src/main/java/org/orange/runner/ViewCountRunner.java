package org.orange.runner;

import org.orange.constans.SystemConstants;
import org.orange.domain.entity.Article;
import org.orange.mapper.ArticleMapper;
import org.orange.service.ArticleService;
import org.orange.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName TestRunner
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/7
 * @Version: 1.0
 */
@Component
public class ViewCountRunner implements CommandLineRunner {
    //CommandLineRunner接口的run方法会在SpringBoot项目启动后自动执行

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RedisCache redisCache;
    @Override
    public void run(String... args) throws Exception {
        //查询博客信息，id和viewCount，存入redis
        List<Article> list=articleMapper.selectList(null);
        Map<String,Integer> viewCountMap=list.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> article.getViewCount().intValue()));
        redisCache.setCacheMap(SystemConstants.ARTICLE_VIEW_COUNT,viewCountMap);

    }
}
