package com.dekuofa.service.impl;

import com.dekuofa.model.entity.Permission;
import com.dekuofa.model.entity.SysRole;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.model.relation.RolePermission;
import com.dekuofa.service.PermissionService;
import io.github.biezhi.anima.page.Page;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.*;
import java.util.stream.Collectors;

import static com.dekuofa.utils.CommonKit.ids2String;
import static io.github.biezhi.anima.Anima.delete;
import static io.github.biezhi.anima.Anima.saveBatch;
import static io.github.biezhi.anima.Anima.select;

/**
 * @author dekuofa <br>
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
                .bySQL(Permission.class, "select p.id, p.name,p.url,p.method  from role_permission rp\n" +
                        "left join t_permission p\n" +
                        "    on rp.permission_id = p.id\n" +
                        "where rp.role_id in (?)", ids).all();

        return new HashSet<>(permissions);
    }

    public Set<String> getPermissionsName(Collection<SysRole> sysRoles) {
        Set<Permission> permissions = getPermissions(sysRoles);
        return permissions.stream()
                .map(Permission::getName).collect(Collectors.toSet());
    }

    public Page<Permission> list(PageParam pageParam) {
        return select().from(Permission.class).page(pageParam.getPage(), pageParam.getLimit());
    }

    @Override
    public List<Permission> permissions(Integer roleId) {
        return select().bySQL(Permission.class,
                "select p.id, p.name, p.url, p.method from t_permission p\n"
                        + "right join role_permission rp\n"
                        + "    on rp.permission_id = p.id\n"
                        + "where rp.role_id = ?", roleId).all();
    }

    @Transient
    @Override
    public void changePermissions(List<RolePermission> permissions, Integer roleId) {
        delete().from(RolePermission.class)
                .where(RolePermission::getRoleId).eq(roleId);
        if (permissions != null && permissions.size() != 0) {
            saveBatch(permissions);
        }
    }
}
