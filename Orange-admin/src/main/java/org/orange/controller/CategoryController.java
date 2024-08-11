package org.orange.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import org.orange.domain.dto.CategoryDto;
import org.orange.domain.entity.Category;
import org.orange.domain.enums.AppHttpCodeEnum;
import org.orange.domain.response.ResponseResult;
import org.orange.domain.vo.CategoryVo;
import org.orange.domain.vo.ExcelCategoryVo;
import org.orange.domain.vo.PageVo;
import org.orange.service.CategoryService;
import org.orange.utils.BeanCopyUtils;
import org.orange.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName CategoryController
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/11
 * @Version: 1.0
 */
@RestController
@RequestMapping("/content/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    //查询所有分类
    @GetMapping("/list")
    public ResponseResult<PageVo> getList(Integer pageNum, Integer pageSize,CategoryDto categoryDto){
        return categoryService.getCategory(pageNum,pageSize,categoryDto);
    }
    //新增分类
    @PostMapping
    public ResponseResult addCategory(@RequestBody Category category){
       return categoryService.addCategory(category);
    }
    //查询单个分类信息
    @GetMapping("/{id}")
    public ResponseResult getCategory(@PathVariable("id") Long id){
        return categoryService.getOneCategory(id);
    }
    //修改分类
    @PutMapping
    public ResponseResult updateCategory(@RequestBody Category category){
        return categoryService.updateCategory(category);
    }
    //删除分类
    @DeleteMapping("/{id}")
    public ResponseResult deleteCategory(@PathVariable("id") List<Long> id){
        return categoryService.deleteCategory(id);
    }
    //导出分类
    @PreAuthorize("@ps.hasPermission('contnet:category:export')")//权限控制,通过自定义判断条件判断用户是否具有操作功能
    @GetMapping("/export")
    public void exportCategory(HttpServletResponse response){
        try {
            //设置下载文件的请求头
            WebUtils.setDownLoadHeader("分类.xlsx",response);
            //获取所有分类
            List<Category> categoryList=categoryService.list();
            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(categoryList, ExcelCategoryVo.class);
            //写入到Excel中
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE).sheet("分类导出").doWrite(excelCategoryVos);
        } catch (Exception e) {
            //出现异常也要响应json
            ResponseResult result=ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }

    }
}
