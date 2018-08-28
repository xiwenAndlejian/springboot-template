package com.dekuofa.manager.impl;

import com.dekuofa.exception.TipException;
import com.dekuofa.manager.RoleManager;
import com.dekuofa.model.entity.SysRole;
import com.dekuofa.service.RoleService;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author gx <br>
 * @date 2018-08-28 <br>
 */
@Component
public class RoleManagerImpl implements RoleManager {

    private RoleService roleService;

    public RoleManagerImpl(RoleService roleService) {
        this.roleService = roleService;
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
}
