package org.orange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.orange.domain.entity.Article;
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
