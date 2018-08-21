package com.dekuofa.service.impl;

import com.dekuofa.model.entity.User;

import com.dekuofa.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static io.github.biezhi.anima.Anima.select;

/**
 * @author gx <br>
 * @date 2018-08-14 <br>
 */
@Service
public class UserServiceImpl implements UserService {

    public User getUser(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        } else {
            User user = select().from(User.class)
                    .where("user_name", username).one();
            return user;
        }
    }

    @Override
    public User findByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        } else {
            User user = select().from(User.class)
                    .where("user_name", username).one();
            return user;
        }
    }
}
