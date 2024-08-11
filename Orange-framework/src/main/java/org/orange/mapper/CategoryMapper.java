package org.orange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.orange.domain.entity.Category;
import org.orange.domain.response.ResponseResult;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
