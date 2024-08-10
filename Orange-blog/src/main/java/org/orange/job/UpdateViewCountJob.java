package org.orange.job;

import org.orange.constans.SystemConstants;
import org.orange.domain.entity.Article;
import org.orange.service.ArticleService;
import org.orange.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName TestJon
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/7
 * @Version: 1.0
 */
@Component
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;

    @Resource
    private ArticleService articleService;//IService中实现了很多批量操作的方法
    //定时任务的思想是通过注解@Scheduled来实现,这里的cron表达式是从0开始,每10秒执行一次
    @Scheduled(cron = "0/600 * * * * ?")//每10分钟执行一次
    public void testJob(){
        //获取redis中浏览量
        Map<String, Integer> viewCountMap = redisCache.getCacheMap(SystemConstants.ARTICLE_VIEW_COUNT);
        //双键集合不能直接转化为流
        List<Article> articleList=viewCountMap.entrySet()
                .stream()
                        .map(entry->new Article(Long.valueOf(entry.getKey()),entry.getValue().longValue()))
                                .collect(Collectors.toList());
        //更新回数据库
        articleService.updateBatchById(articleList);
    }
}
