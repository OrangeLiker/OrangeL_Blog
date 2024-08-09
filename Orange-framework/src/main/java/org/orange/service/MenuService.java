package org.orange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.orange.domain.entity.Menu;

import java.util.List;

public interface MenuService extends IService<Menu> {
    List<String> selectPermsByUserId(Long id);
}
