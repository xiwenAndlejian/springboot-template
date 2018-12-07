package com.dekuofa.service;

import com.dekuofa.model.entity.SysRole;
import com.dekuofa.model.param.SysRoleParam;
import com.dekuofa.model.relation.UserRole;
import io.swagger.models.auth.In;

import java.util.Collection;
import java.util.List;
import java.util.Set;


/**
 * @author dekuofa <br>
 * @date 2018-08-20 <br>
 */
public interface RoleService {
    Set<SysRole> getRoles(Integer userId);

    Integer addRole(SysRole role);
    List<Integer> getRoleIds(String[] roles);

    void modify(SysRole role);

    Collection<SysRole> list();

    boolean isExist(String roleName);

    void addUserRoles(Integer userId, List<Integer> roleIds);

    void changeUserRoles(Integer userId, List<UserRole> addRoleIds, List<Integer> deleteIds);
}
