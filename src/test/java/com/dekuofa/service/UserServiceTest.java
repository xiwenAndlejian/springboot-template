package com.dekuofa.service;

import com.dekuofa.model.BaseUserInfo;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.common.BeanMethod;
import com.dekuofa.model.entity.SysRole;
import com.dekuofa.model.entity.User;
import com.dekuofa.utils.CommonKit;
import com.dekuofa.utils.DateUtil;
import com.dekuofa.utils.ShaUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author gx <br>
 * @date 2018-08-20 <br>
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void findUser() {
        User user = userService.findByUsername("test");
        if (user != null)
            log.info(user.toString());
    }

    @Test
    public void test() {
//        log.info("now: {}", now);
//        log.info("userInfo: {}", userInfo.toString());
//        user.setModifyInfo(userInfo, now).setCreateInfo(userInfo, now);
    }

    @Test
    public void test_1() {
//        log.info(""+ int.class.isAssignableFrom(Integer.TYPE));
        log.info(ShaUtil.sha512Encode("123456"));
//        BeanMethod.BeanMethodBuilder builder = BeanMethod.builder();
//        BeanMethod method = builder.field("username").build();
//        log.info(method.getMethod());
    }
}
