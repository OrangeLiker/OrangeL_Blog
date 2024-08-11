package org.orange.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName ExcelCategoryVo
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/11
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelCategoryVo {
    @ExcelProperty(value = "分类名称")
    private String name;
    @ExcelProperty(value = "分类描述")
    private String description;
    @ExcelProperty(value = "分类状态")
    private String status;
}
