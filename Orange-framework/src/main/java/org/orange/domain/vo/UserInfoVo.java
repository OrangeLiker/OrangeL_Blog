package org.orange.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName UserInfoVo
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/5
 * @Version: 1.0
 */
@Data
@Accessors(chain = true)
public class UserInfoVo {
    /**
     * 主键
     */
    private Long id;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String avatar;
    private String sex;
    private String email;
}