package org.orange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.orange.constans.SystemConstants;
import org.orange.domain.entity.Article;
import org.orange.domain.entity.Category;
import org.orange.domain.response.ResponseResult;
import org.orange.domain.vo.ArticleDetailVo;
import org.orange.domain.vo.ArticleListVo;
import org.orange.domain.vo.HotArticleVo;
import org.orange.domain.vo.PageVo;
import org.orange.mapper.ArticleMapper;
import org.orange.service.ArticleService;
import org.orange.service.CategoryService;
import org.orange.utils.BeanCopyUtils;
import org.orange.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @BelongsProject: Orange_Blog
 * @ClassName ArticleImpl
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/4
 * @Version: 1.0
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;
    //热门文章查询
    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper <Article> queryWrapper=new LambdaQueryWrapper<>();
        //必须已发布
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //按照浏览量排序
        //TODO 从redis中查询浏览量排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //只查询前10条
        Page <Article> page=new Page<>(1,10);
        page(page,queryWrapper);
        List<Article> articles=page.getRecords();

        //使用Bean拷贝封装为Vo返回
        List<HotArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        return ResponseResult.okResult(articleVos);
    }

    @Override
    public ResponseResult articleList(Long categoryId, Long pageNum, Long pageSize) {
        //查询条件
        LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();
        //category可有可无，所以要做判断
        queryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);//如果categoryId不为空且大于0，就加入查询条件
        //正式发布的
        queryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        //置顶显示
        queryWrapper.orderByDesc(Article::getIsTop);
        //分页查询
        Page <Article> page=new Page<>(pageNum,pageSize);
        page(page,queryWrapper);//分页查询

        //根据id查name
        List<Article> articles=page.getRecords();
//        for循环遍历
//        for(Article article:articles){
//            Category category = categoryService.getById(article.getCategoryId());
//            article.setCategoryName(category.getName());
//        }
        //Stream流
        articles.stream()
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());

        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos,page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article=getById(id);
        //从redis中查询viewCount
        Integer redisViewCount = redisCache.getCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT, id.toString());
        article.setViewCount(redisViewCount.longValue());
        //转化成Vo
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //查询分类名
        Long categoryId=articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if(category!=null){
            articleDetailVo.setCategoryName(category.getName());
        }
        //封装响应对象
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        redisCache.incrementViewCount(SystemConstants.ARTICLE_VIEW_COUNT,id.toString(),1);
        return ResponseResult.okResult();
    }

}
