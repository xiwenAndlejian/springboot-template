package com.dekuofa.service;

import com.dekuofa.model.entity.Permission;
import com.dekuofa.model.entity.SysRole;

import java.util.Collection;
import java.util.Set;

/**
 * @author gx <br>
 * @date 2018-08-20 <br>
 */
public interface PermissionService {
    Set<Permission> getPermissions(Collection<SysRole> roles);
}
