package com.dekuofa.service;

import com.dekuofa.model.entity.SysRole;

import java.util.Set;


/**
 * @author gx <br>
 * @date 2018-08-20 <br>
 */
public interface RoleService {
    Set<SysRole> getRoles(int userId);
}
