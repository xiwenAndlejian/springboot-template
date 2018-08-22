package com.dekuofa.manager.impl;

import com.dekuofa.manager.UserManager;
import com.dekuofa.model.entity.Permission;
import com.dekuofa.model.entity.SysRole;
import com.dekuofa.model.entity.User;
import com.dekuofa.service.PermissionService;
import com.dekuofa.service.RoleService;
import com.dekuofa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * @author gx <br>
 * @date 2018-08-22 <br>
 */
@Component
public class UserManagerImpl implements UserManager {

    private UserService       userService;
    private RoleService       roleService;
    private PermissionService permissionService;

    @Autowired
    public UserManagerImpl(UserService userService, RoleService roleService, PermissionService permissionService) {
        this.userService = userService;
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    @Override
    public User findByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        User user = userService.findByUsername(username);
        if (user != null) {
            Collection<SysRole> roles = roleService.getRoles(user.getId());
            Collection<Permission> permissions = permissionService.getPermissions(roles);
            user.setSysRoles(roles);
            user.setPermissions(permissions);
        }
        return user;
    }

    @Override
    public void login(int userId, String ip) {

    }
}
