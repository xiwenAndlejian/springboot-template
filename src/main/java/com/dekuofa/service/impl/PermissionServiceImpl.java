package com.dekuofa.service.impl;

import com.dekuofa.model.entity.Permission;
import com.dekuofa.model.entity.SysRole;
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

    @Override
    public Set<Permission> getPermissions(Collection<SysRole> sysRoles) {
        if (sysRoles == null || sysRoles.isEmpty()) {
            return new HashSet<>();
        }

        String ids = ids2String(sysRoles);
        List<Permission> permissions = select()
                .bySQL(Permission.class, "select p.id, p.permission  from role_permission rp\n" +
                        "left join permission p\n" +
                        "    on rp.permission_id = p.id\n" +
                        "where rp.role_id in (?)", ids).all();

        return new HashSet<>(permissions);
    }

    public Set<String> getPermissionsName(Collection<SysRole> sysRoles) {
        Set<Permission> permissions = getPermissions(sysRoles);
        return permissions.stream()
                .map(Permission::getName).collect(Collectors.toSet());
    }

}
