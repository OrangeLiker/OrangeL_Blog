package org.orange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.orange.constans.SystemConstants;
import org.orange.domain.entity.Article;
import org.orange.domain.entity.Category;
import org.orange.domain.response.ResponseResult;
import org.orange.domain.vo.CategoryVo;
import org.orange.mapper.CategoryMapper;
import org.orange.service.ArticleService;
import org.orange.service.CategoryService;
import org.orange.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName CategoryServiceImpl
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/4
 * @Version: 1.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public ResponseResult getCategoryList() {
        //查询文章表，状态为已发布的文章
        LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList=articleService.list(queryWrapper);
        //根据文章获取分类id并去重
        Set<Long> categoryIds = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());
        //根据分类id查询分类表，获取分类信息，封装返回
        List<Category> categories = listByIds(categoryIds);
        categories=categories.stream()
                .filter(category -> category.getStatus().equals(SystemConstants.Category_STATUS_NORMAL))
                .collect(Collectors.toList());
        //封装Vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }

    @Override
    public ResponseResult getAllCategory() {
        LambdaQueryWrapper<Category> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.eq(Category::getStatus,SystemConstants.Category_STATUS_NORMAL);
        queryWrapper.eq(Category::getDelFlag,SystemConstants.Category_STATUS_NORMAL);
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }
}
