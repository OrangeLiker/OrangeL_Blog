package org.orange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.orange.domain.dto.CategoryDto;
import org.orange.domain.entity.Category;
import org.orange.domain.response.ResponseResult;
import org.orange.domain.vo.CategoryVo;
import org.orange.domain.vo.PageVo;

import java.util.List;

public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();

    List<CategoryVo> getAllCategory();

    ResponseResult<PageVo> getCategory(Integer pageNum, Integer pageSize, CategoryDto categoryDto);

    ResponseResult addCategory(Category category);

    ResponseResult getOneCategory(Long id);

    ResponseResult updateCategory(Category category);

    ResponseResult deleteCategory(List<Long> id);

}
