package org.orange.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.orange.domain.entity.ArticleTag;
import org.orange.mapper.ArticleTagMapper;
import org.orange.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName ArticleTagServiceImpl
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/11
 * @Version: 1.0
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag>implements ArticleTagService {
}
