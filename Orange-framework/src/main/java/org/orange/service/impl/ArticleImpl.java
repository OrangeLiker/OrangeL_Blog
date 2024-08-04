package org.orange.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.orange.domain.entity.Article;
import org.orange.mapper.ArticleMapper;
import org.orange.service.ArticleService;
import org.springframework.stereotype.Service;

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
}
