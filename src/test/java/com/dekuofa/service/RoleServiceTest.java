package com.dekuofa.service;

import com.dekuofa.BaseTest;
import com.dekuofa.model.NormalUserInfo;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.entity.SysRole;
import com.dekuofa.model.enums.UserType;
import com.dekuofa.model.relation.UserRole;
import com.dekuofa.utils.DateUtil;
import io.github.biezhi.anima.Anima;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;
import org.sql2o.converters.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.dekuofa.utils.DateUtil.newUnixMilliSecond;

/**
 * @author dekuofa <br>
 * @date 2018-08-28 <br>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class RoleServiceTest extends BaseTest {

    @Autowired
    private RoleService roleService;

    @Autowired
    private Environment env;


    /**
     * 创建 anima bean， 类似于dataSource
     */
//    @Before
//    @Bean
//    public Anima anima() {
//        String url      = env.getProperty("spring.datasource.url");
//        String username = env.getProperty("spring.datasource.username");
//        String password = env.getProperty("spring.datasource.password");
//        assert !StringUtils.isEmpty(url);
//        assert !StringUtils.isEmpty(username);
//        assert !StringUtils.isEmpty(password);
//
//        return Anima.open(url, username, password);
//    }

    @Test
    public void test() {
        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(new UserRole(1, 1));
        userRoles.add(new UserRole(1, 2));
        List<Integer> deleteIds = Arrays.asList(1, 2);
        roleService.changeUserRoles(1, userRoles, deleteIds);
    }

    @Test
    public void test_1() {
        SysRole  role     = new SysRole();
        UserInfo userInfo = getUserInfo();
        Long current = getTime();

        role.setName("editor");
        role.setDesc("编辑者");
        role.setCreateInfo(userInfo, current).setModifyInfo(userInfo, current);
        roleService.addRole(role);
        log.info("roleId:" + role.getId());
//        (ConnectionImpl) anima.getSql2o().open();
    }

}
