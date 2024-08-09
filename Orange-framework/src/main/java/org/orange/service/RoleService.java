package org.orange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.orange.domain.entity.Role;

import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName RoleService
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/8
 * @Version: 1.0
 */
public interface RoleService extends IService<Role> {
    List<String> selectRoleKeyByUserId(Long id);
}
