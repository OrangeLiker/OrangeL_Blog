package org.orange.service.impl;

import org.orange.domain.entity.User;
import org.orange.mapper.UserMapper;
import org.orange.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ps")
public class PermissionService {
    //判断权限
    public boolean hasPermission(String permission) {
        System.out.println(permission);
        if (SecurityUtils.adminOrUser(SecurityUtils.getUserId())){
            return true;
        }else{
            List<String> permissions = SecurityUtils.getLoginUser().getPermission();
            return permissions.contains(permission);
        }
    }
}
