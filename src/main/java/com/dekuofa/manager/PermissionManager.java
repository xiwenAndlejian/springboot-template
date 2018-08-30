package com.dekuofa.manager;

import com.dekuofa.model.entity.Permission;
import com.dekuofa.model.param.PageParam;
import io.github.biezhi.anima.page.Page;

import java.util.List;

/**
 * @author dekuofa <br>
 * @date 2018-08-28 <br>
 */
public interface PermissionManager {

    Page<Permission> list(PageParam pageParam);

    List<Permission> permissions(Integer roleId);

    void changePermissions(Integer roleId, List<Integer> permissionIds);
}
