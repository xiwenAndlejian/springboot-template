package com.dekuofa.service.impl;

import com.dekuofa.exception.TipException;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.entity.SysRole;
import com.dekuofa.model.entity.User;

import com.dekuofa.model.param.PageParam;
import com.dekuofa.service.UserService;
import com.dekuofa.utils.DateUtil;
import io.github.biezhi.anima.core.Joins;
import io.github.biezhi.anima.core.functions.TypeFunction;
import io.github.biezhi.anima.enums.OrderBy;
import io.github.biezhi.anima.page.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.beans.Transient;

import static com.dekuofa.utils.DateUtil.newUnixMilliSecond;
import static io.github.biezhi.anima.Anima.save;
import static io.github.biezhi.anima.Anima.select;
import static io.github.biezhi.anima.Anima.update;

/**
 * @author dekuofa <br>
 * @date 2018-08-14 <br>
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User findByUsername(String username) {
        User user = select().from(User.class)
                .where("user_name", username).one();
        return user;
    }

    @Transient
    @Override
    public Integer addUser(User user) throws TipException {
        try {
            Integer id = save(user).asInt();
            if (null == id) {
                throw new TipException("主键id获取失败");
            }
            return id;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void changePassword(Integer userId, String newPasswd, UserInfo userInfo) throws TipException {
        update().from(User.class)
                .where(User::getId).eq(userId)
                .set(User::getPassword, newPasswd)
                .set(User::getModifyTime, newUnixMilliSecond())
                .set(User::getModifierId, userInfo.getUserId())
                .set(User::getModifierName, userInfo.getNickName())
                .execute();
    }

    @Override
    public void changeAvatar(Integer userId, String avatar, UserInfo userInfo) throws TipException {
        update().from(User.class)
                .where(User::getId).eq(userId)
                .set(User::getAvatar, avatar)
                .set(User::getModifyTime, newUnixMilliSecond())
                .set(User::getModifierId, userInfo.getUserId())
                .set(User::getModifierName, userInfo.getNickName())
                .execute();
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
                .set(User::getLastLoginIp, ip)
                .set("last_login_time", DateUtil.newUnixMilliSecond())
                .where(User::getId).eq(userId)
                .execute();
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
