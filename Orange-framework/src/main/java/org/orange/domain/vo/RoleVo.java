package org.orange.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName RoleVo
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/12
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo {
    private Long id;
    private String roleName;
    private String roleKey;
    private Integer roleSort;
    private String status;
    private Date createTime;
}
