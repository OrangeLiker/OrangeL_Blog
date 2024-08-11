package org.orange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.orange.domain.entity.Category;
import org.orange.domain.response.ResponseResult;

public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();

    ResponseResult getAllCategory();
}
