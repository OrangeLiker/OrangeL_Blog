package org.orange.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.orange.domain.entity.Role;
import org.orange.domain.entity.User;

import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName AdminUserVo
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/13
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserVo {
    private User user;
    private List<Role> roles;
    private List<Long> roleIds;
}
