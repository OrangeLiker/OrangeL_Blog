package org.orange.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName ChangePasswordDto
 * @Description 修改密码实体
 * @Author WangZJ0908
 * @Date 2025/1/2
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDto {
    Long userId;
    String oldPassword;
    String newPassword;
}
