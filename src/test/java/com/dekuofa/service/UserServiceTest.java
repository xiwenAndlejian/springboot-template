package com.dekuofa.service;

import com.dekuofa.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        log.info(user.toString());
    }
}
