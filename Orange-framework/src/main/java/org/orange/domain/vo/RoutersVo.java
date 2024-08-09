package org.orange.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.orange.domain.entity.Menu;

import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName RoutersVo
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/9
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutersVo {

    private List<Menu> menus;
}
