package com.dekuofa.service;

import com.dekuofa.model.entity.SysRole;
import com.dekuofa.model.param.SysRoleParam;

import java.util.Collection;
import java.util.List;
import java.util.Set;


/**
 * @author gx <br>
 * @date 2018-08-20 <br>
 */
public interface RoleService {
    Set<SysRole> getRoles(Integer userId);

    Integer addRole(SysRole role);

    void modify(SysRole role);

    Collection<SysRole> list();


    boolean isExist(String roleName);
}
