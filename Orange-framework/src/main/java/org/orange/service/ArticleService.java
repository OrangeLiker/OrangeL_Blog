package org.orange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.orange.domain.dto.ArticleDto;
import org.orange.domain.entity.Article;
import org.orange.domain.response.ResponseResult;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Long categoryId, Long pageNum, Long pageSize);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult addArticle(ArticleDto articleDto);
}
