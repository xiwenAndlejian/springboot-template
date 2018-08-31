package com.dekuofa.service;

import com.dekuofa.model.relation.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dekuofa <br>
 * @date 2018-08-28 <br>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void test() {
        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(new UserRole(1, 1));
        userRoles.add(new UserRole(1, 2));
        List<Integer> deleteIds = Arrays.asList(1, 2);
        roleService.changeUserRoles(1, userRoles, deleteIds);
    }

}
