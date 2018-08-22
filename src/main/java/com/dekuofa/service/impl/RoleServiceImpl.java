package com.dekuofa.service.impl;


import com.dekuofa.model.entity.SysRole;
import com.dekuofa.model.entity.User;
import com.dekuofa.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.github.biezhi.anima.Anima.select;

/**
 * @author gx <br>
 * @date 2018-08-14 <br>
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public Set<SysRole> getRoles(int userId) {
        List<SysRole> sysRoles = select()
                .bySQL(SysRole.class, "select r.id, r.name from t_role r\n" +
                        "right join user_role ur\n" +
                        "    on r.id = ur.role_id\n" +
                        "where ur.user_id = ?", userId).all();

        return new HashSet<>(sysRoles);
    }
}
