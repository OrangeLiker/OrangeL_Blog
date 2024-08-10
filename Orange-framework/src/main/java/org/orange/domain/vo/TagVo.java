package org.orange.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName TagVo
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/10
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagVo {

    private Long id;

    private String name;

    private String remark;
}