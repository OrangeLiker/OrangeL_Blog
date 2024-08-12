package org.orange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.orange.domain.entity.Menu;
import org.orange.domain.response.ResponseResult;

import java.util.List;

public interface MenuService extends IService<Menu> {
    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    ResponseResult getMenuList(String status, String menuName);

    ResponseResult addMenu(Menu menu);

    ResponseResult getMenu(Long id);

    ResponseResult updateMenu(Menu menu);

    ResponseResult deleteMenu(Long id);

    ResponseResult getMenuTree();

    ResponseResult roleMenuTreeselect(Long id);
}
