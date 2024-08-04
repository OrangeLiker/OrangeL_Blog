package org.orange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.orange.domain.entity.Article;
import org.orange.domain.response.ResponseResult;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();
}
