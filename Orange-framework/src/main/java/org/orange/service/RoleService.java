package org.orange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.orange.domain.dto.RoleDto;
import org.orange.domain.dto.StatusDto;
import org.orange.domain.entity.Role;
import org.orange.domain.response.ResponseResult;

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

    ResponseResult getRoleList(Integer pageNum, Integer pageSize, String roleName, String status);

    ResponseResult changeStatus(StatusDto statusDto);

    ResponseResult addRole(RoleDto roleDto);

    ResponseResult getRole(Long id);

    ResponseResult updateRole(RoleDto roleDto);

    ResponseResult deleteRole(List<Long> id);
}
