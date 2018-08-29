package com.dekuofa.service;

import com.dekuofa.model.BaseUserInfo;
import com.dekuofa.model.entity.User;
import com.dekuofa.model.param.PageParam;
import io.github.biezhi.anima.page.Page;


/**
 * @author gx <br>
 * @date 2018-08-20 <br>
 */
public interface UserService {
    User findByUsername(String username);

    Integer addUser(User user, BaseUserInfo userInfo);

    boolean isExist(String username);

    boolean isExist(Integer userId);

    Page<User> query(String username, PageParam pageParam);

    void login(Integer userId, String ip);

    void modify(User user);

    User getUser(Integer userId);

}
