package org.orange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.orange.domain.entity.Article;
import org.orange.domain.response.ResponseResult;
import org.orange.mapper.ArticleMapper;
import org.orange.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName ArticleImpl
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/4
 * @Version: 1.0
 */
@Service
public class ArticleImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    //热门文章查询
    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper <Article> queryWrapper=new LambdaQueryWrapper<>();
        //必须已发布
        queryWrapper.eq(Article::getStatus,"0");
        //按照浏览量排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //只查询前10条
        Page <Article> page=new Page<>(1,10);
        page(page,queryWrapper);
        List<Article> articles=page.getRecords();
        return ResponseResult.okResult(articles);
    }
}
