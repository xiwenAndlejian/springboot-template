package com.dekuofa.service;

import com.dekuofa.model.entity.User;

/**
 * @author gx <br>
 * @date 2018-08-20 <br>
 */
public interface UserService {
    User findByUsername(String username);
}
