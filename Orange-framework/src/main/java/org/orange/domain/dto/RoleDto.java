package org.orange.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.attribute.standard.PageRanges;
import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName RoleDto
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/12
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private Long id;
    private String roleName;
    private String roleKey;
    private Integer roleSort;
    private String status;
    private String remark;
    private List<Long> menuIds;
}
