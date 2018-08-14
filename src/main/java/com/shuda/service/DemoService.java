package com.shuda.service;

import com.shuda.model.entity.User;
import io.github.biezhi.anima.Anima;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static io.github.biezhi.anima.Anima.select;

/**
 * @author gx <br>
 * @date 2018-08-08 <br>
 */
@Service
@Slf4j
public class DemoService {

    public void test() {
        User user = select().from(User.class)
                .where("id = ?", 1)
                .one();
        log.info("user: {}", user);
        System.out.println(user.toString());
    }
}
