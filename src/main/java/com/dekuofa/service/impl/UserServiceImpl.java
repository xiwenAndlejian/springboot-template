package com.dekuofa.service.impl;

import com.dekuofa.model.BaseUserInfo;
import com.dekuofa.model.entity.User;

import com.dekuofa.model.param.PageParam;
import com.dekuofa.service.UserService;
import com.dekuofa.utils.DateUtil;
import io.github.biezhi.anima.enums.OrderBy;
import io.github.biezhi.anima.page.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.beans.Transient;

import static io.github.biezhi.anima.Anima.save;
import static io.github.biezhi.anima.Anima.select;
import static io.github.biezhi.anima.Anima.update;

/**
 * @author dekuofa <br>
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
        User user = select().from(User.class)
                .where("user_name", username).one();
        return user;
    }

    @Transient
    @Override
    public Integer addUser(User user, BaseUserInfo userInfo) {



        try {
            Integer id = save(user).asInt();
            return id;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean isExist(String username) {
        long count = select().from(User.class).where("user_name", username).count();
        return count >= 1;
    }

    @Override
    public boolean isExist(Integer userId) {
        long count = select().from(User.class).where("id", userId).count();
        return count >= 1;
    }

    @Override
    public Page<User> query(String username, PageParam pageParam) {
        Page<User> users;
        if (StringUtils.isEmpty(username)) {
            users = select().from(User.class)
                    .order("create_time", OrderBy.DESC)
                    .page(pageParam.getPage(), pageParam.getLimit());
        } else {
            users = select().from(User.class)
                    .like("user_name" ,"%" + username + "%")
                    .order("create_time", OrderBy.DESC)
                    .page(pageParam.getPage(), pageParam.getLimit());
        }

        return users;
    }

    @Override
    public void login(Integer userId, String ip) {
        update().from(User.class)
                .set(User::getLastLoginIp, "ip")
                .set(User::getLastLoginTime, DateUtil.newUnixMilliSecond())
                .where(User::getId).eq(userId).execute();
    }

    @Override
    public void modify(User user) {
        user.update();
    }

    @Override
    public User getUser(Integer userId) {
        return select().from(User.class).byId(userId);
    }
}
