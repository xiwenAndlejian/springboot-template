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
        User user = select().from(User.class)
                .where("user_name", username).one();
        return user;
    }

    @Transient
    @Override
    public int addUser(User user, BaseUserInfo userInfo) {

        int now = DateUtil.newUnix();
        user.setCreateTime(now);
        user.setModifyTime(now);

        int id = save(user).asInt();
        return id;
    }

    @Override
    public boolean isExist(String username) {
        long count = select().from(User.class).where("user_name", username).count();
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
    public void login(int userId, String ip) {
        update().from(User.class)
                .set(User::getLastLoginIp, "ip")
                .set(User::getLastLoginTime, DateUtil.newUnix())
                .where(User::getId).eq(2).execute();
    }

    @Override
    public void modify(User user) {
        user.update();
    }

    @Override
    public User getUser(int userId) {
        return select().from(User.class).byId(userId);
    }
}
