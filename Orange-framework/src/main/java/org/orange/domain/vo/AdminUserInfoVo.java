package org.orange.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName AdminUserInfoVo
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/8
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserInfoVo {
   private List<String> permissions;
   private List<String> roles;
   private UserInfoVo user;
}
