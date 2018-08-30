package com.dekuofa.manager.impl;


import com.dekuofa.manager.PermissionManager;
import com.dekuofa.model.entity.Permission;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.service.PermissionService;
import io.github.biezhi.anima.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author dekuofa <br>
 * @date 2018-08-28 <br>
 */
@Component
public class PermissionMangerImpl implements PermissionManager {
    private PermissionService permissionService;

    @Autowired
    public PermissionMangerImpl(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public Page<Permission> list(PageParam pageParam) {
        return permissionService.list(pageParam);
    }

    @Override
    public List<Permission> permissions(Integer roleId) {
        return permissionService.permissions(roleId);
    }

    @Override
    public void changePermissions(Integer roleId, List<Integer> permissionIds) {
        //1. 查询当前角色的权限列表
        //2.
    }
}
