package com.dekuofa.service;

import com.dekuofa.model.entity.User;
import com.dekuofa.utils.ShaUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author dekuofa <br>
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
