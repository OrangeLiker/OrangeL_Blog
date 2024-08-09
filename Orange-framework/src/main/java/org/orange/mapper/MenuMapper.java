package org.orange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.orange.domain.entity.Menu;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {


    List<Menu> selectAllRoutersMenu();

    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}
