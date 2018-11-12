package com.dekuofa.service;

import com.dekuofa.exception.TipException;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.entity.User;
import com.dekuofa.model.param.PageParam;
import io.github.biezhi.anima.page.Page;


/**
 * @author dekuofa <br>
 * @date 2018-08-20 <br>
 */
public interface UserService {
    User findByUsername(String username);

    Integer addUser(User user) throws TipException;

    void changePassword(Integer userId, String newPasswd, UserInfo userInfo) throws TipException;

    void changeAvatar(Integer userId, String avatar, UserInfo userInfo) throws TipException;

    boolean isExist(String username);

    boolean isExist(Integer userId);

    Page<User> query(String username, PageParam pageParam);

    void login(Integer userId, String ip);

    void modify(User user) throws TipException;

    User getUser(Integer userId);

}
