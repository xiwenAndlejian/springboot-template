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

    int addUser(User user, BaseUserInfo userInfo);

    boolean isExist(String username);

    Page<User> query(String username, PageParam pageParam);

    void login(int userId, String ip);

    void modify(User user);

    User getUser(int userId);

}
