package org.orange.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.orange.domain.entity.Menu;

import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName MenuTreeVo
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/12
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuTreeVo {
    private List<Menu> menus;
    private List<Long> checkedKeys;
}
