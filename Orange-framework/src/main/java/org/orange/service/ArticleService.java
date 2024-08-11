package org.orange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.orange.domain.dto.ArticleDto;
import org.orange.domain.entity.Article;
import org.orange.domain.response.ResponseResult;
import org.orange.domain.vo.UpdateArticleVo;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Long categoryId, Long pageNum, Long pageSize);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult addArticle(ArticleDto articleDto);

    ResponseResult getArticleList(Integer pageNum, Integer pageSize, ArticleDto articleDto);

    ResponseResult getUpdateArticle(Long id);

    ResponseResult updateArticle(UpdateArticleVo updateArticleVo);
}
