package com.dekuofa.manager;

import com.dekuofa.model.entity.Permission;
import com.dekuofa.model.entity.SysRole;
import io.swagger.models.auth.In;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author dekuofa <br>
 * @date 2018-08-28 <br>
 */
public interface RoleManager {
    Collection<SysRole> roles(Integer userId);

    Integer addRole(SysRole sysRole);

    boolean isExist(SysRole sysRole);

    void modify(SysRole role);

    Collection<SysRole> list();

    List<Integer> roleIds(Integer userId);

    void changeUserRoles(Integer userId, Set<Integer> addRoleIds, Set<Integer> deleteRoleIds);
}