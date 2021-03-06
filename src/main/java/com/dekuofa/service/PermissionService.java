package com.dekuofa.service;

import com.dekuofa.model.entity.Permission;
import com.dekuofa.model.entity.SysRole;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.model.relation.RolePermission;
import io.github.biezhi.anima.page.Page;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author dekuofa <br>
 * @date 2018-08-20 <br>
 */
public interface PermissionService {
    Set<Permission> getPermissions(Collection<SysRole> roles);

    Page<Permission> list(PageParam pageParam);

    List<Permission> permissions(Integer roleId);

    void changePermissions(List<RolePermission> permissions, Integer roleId);
}
