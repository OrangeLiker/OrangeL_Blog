package org.orange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.orange.domain.entity.Link;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName LinkMapper
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/5
 * @Version: 1.0
 */
@Mapper
public interface LinkMapper extends BaseMapper<Link> {
}
