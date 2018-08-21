package com.dekuofa.service.impl;

import com.dekuofa.model.entity.Permission;
import com.dekuofa.model.entity.Role;
import com.dekuofa.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.dekuofa.utils.CommonKit.ids2String;
import static io.github.biezhi.anima.Anima.select;

/**
 * @author gx <br>
 * @date 2018-08-14 <br>
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    public Set<Permission> getPermissions(List<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return new HashSet<>();
        }

        String ids = ids2String(roles);
        List<Permission> permissions = select()
                .bySQL(Permission.class, "select p.id, p.permission  from role_permission rp\n" +
                        "left join permission p\n" +
                        "    on rp.permission_id = p.id\n" +
                        "where rp.role_id in (?)", ids).all();
        Set<Permission> permissionSet = new HashSet<>(permissions);
        return permissionSet;
    }

    public Set<String> getPermissionsName(List<Role> roles) {
        Set<Permission> permissions = getPermissions(roles);
        return permissions.stream()
                .map(Permission::getPermission).collect(Collectors.toSet());
    }
}
