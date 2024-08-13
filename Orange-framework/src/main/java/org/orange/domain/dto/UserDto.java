package org.orange.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName UserDto
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/12
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String userName;
    private String nickName;
    private String password;
    private String phonenumber;
    private String email;
    private String sex;
    private String status;
    private List<Long> roleIds;
}
