package org.orange.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName StatusDto
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/12
 * @Version: 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatusDto {
    private Long roleId;
    private String status;
}
