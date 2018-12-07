package com.dekuofa.manager.impl;

import com.dekuofa.exception.TipException;
import com.dekuofa.manager.RoleManager;
import com.dekuofa.model.entity.Permission;
import com.dekuofa.model.entity.SysRole;
import com.dekuofa.model.relation.UserRole;
import com.dekuofa.service.PermissionService;
import com.dekuofa.service.RoleService;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author dekuofa <br>
 * @date 2018-08-28 <br>
 */
@Component
public class RoleManagerImpl implements RoleManager {

    private RoleService       roleService;
    private PermissionService permissionService;

    public RoleManagerImpl(RoleService roleService,
                           PermissionService permissionService) {
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    @Override
    public Collection<SysRole> roles(Integer userId) {
        return roleService.getRoles(userId);
    }

    @Override
    public Integer addRole(SysRole sysRole) {
        if (roleService.isExist(sysRole.getName())) {
            throw new TipException("新增角色失败：角色名不能重复");
        }
        return roleService.addRole(sysRole);
    }

    @Override
    public boolean isExist(SysRole sysRole) {
        return roleService.isExist(sysRole.getName());
    }

    @Override
    public void modify(SysRole role) {
        roleService.modify(role);
    }

    @Override
    public Collection<SysRole> list() {
        return roleService.list();
    }

    @Override
    public List<Integer> roleIds(Integer userId) {
        return roleService.getRoles(userId)
                .stream()
                .map(SysRole::getId)
                .collect(Collectors.toList());
    }

    @Override
    public void changeUserRoles(Integer userId, Set<Integer> addRoleIds, Set<Integer> deleteRoleIds) {
        List<UserRole> addUserRoles = addRoleIds.stream()
                .map(roleId -> new UserRole(userId, roleId))
                .collect(Collectors.toList());
        List<Integer> deleteRoles = new ArrayList<>(deleteRoleIds);
        roleService.changeUserRoles(userId, addUserRoles, deleteRoles);
    }
}
