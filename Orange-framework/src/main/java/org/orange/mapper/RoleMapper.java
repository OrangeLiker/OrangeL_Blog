package org.orange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.orange.domain.entity.Role;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
