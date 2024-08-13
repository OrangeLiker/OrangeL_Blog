package org.orange.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName LinkDto
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/13
 * @Version: 1.0
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class LinkDto {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String logo;
    private String status;
}
