package com.dekuofa.service.impl;


import com.dekuofa.model.entity.Role;
import com.dekuofa.model.entity.User;
import com.dekuofa.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<Role> getRoles(User user) {
        if (user == null) {
            return new ArrayList<>();
        }
        List<Role> roles = select()
                .bySQL(Role.class, "select r.id, r.name from roles r\n" +
                        "right join user_role ur\n" +
                        "    on r.id = ur.role_id\n" +
                        "where ur.user_id = ?", user.getId()).all();
        return roles;
    }

    public Set<String> getRoleNames(User user) {
        List<Role> roles = getRoles(user);
        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }
}
