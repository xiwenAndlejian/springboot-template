package com.dekuofa.service.impl;


import com.dekuofa.exception.TipException;
import com.dekuofa.model.entity.SysRole;
import com.dekuofa.service.RoleService;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.*;

import static io.github.biezhi.anima.Anima.save;
import static io.github.biezhi.anima.Anima.select;
import static io.github.biezhi.anima.Anima.update;

/**
 * @author gx <br>
 * @date 2018-08-14 <br>
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public Set<SysRole> getRoles(Integer userId) {
        List<SysRole> sysRoles = select()
                .bySQL(SysRole.class, "select r.id, r.name from t_role r\n" +
                        "right join user_role ur\n" +
                        "    on r.id = ur.role_id\n" +
                        "where ur.user_id = ?", userId).all();

        return new HashSet<>(sysRoles);
    }

    @Transient
    @Override
    public Integer addRole(SysRole role) {
        Integer id = save(role).asInt();
        if (id == null) {
            throw new TipException("服务器异常：新增用户失败");
        }
        return id;

    }

    @Override
    public void modify(SysRole role) {
        role.update();
    }

    @Override
    public Collection<SysRole> list() {
        return select().from(SysRole.class).all();
    }

    @Override
    public boolean isExist(String roleName) {
        long count = select().from(SysRole.class)
                .where("name", roleName)
                .count();
        return count >= 1;
    }

}
