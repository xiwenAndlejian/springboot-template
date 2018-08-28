package com.dekuofa.manager;

import com.dekuofa.model.entity.SysRole;

import java.util.Collection;

/**
 * @author gx <br>
 * @date 2018-08-28 <br>
 */
public interface RoleManager {
    Collection<SysRole> roles(Integer userId);

    Integer addRole(SysRole sysRole);

    boolean isExist(SysRole sysRole);

    void modify(SysRole role);

    Collection<SysRole> list();
}