package com.dekuofa.manager;


import com.dekuofa.exception.TipException;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.entity.User;
import com.dekuofa.model.entity.UserDetail;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.model.param.PasswdParam;
import io.github.biezhi.anima.page.Page;


/**
 * @author dekuofa <br>
 * @date 2018-08-22 <br>
 */
public interface UserManager {

    /**
     * 查找用户（会包含用户的角色&权限）
     * 当用户名为null或空字符串或者用户不存在时，会返回null
     */
    User findByUsername(String username);

    /**
     * 更新用户登录信息：最后一次登陆时间 & ip
     */
    void login(Integer userId, String ip);

    Integer addUser(User user, UserInfo userInfo, String[] roles) throws TipException;

    Page<User> queryUser(String username, PageParam pageParam);

    void updateUserDetail(UserDetail userDetail, UserInfo userInfo) throws TipException;

    void changePassword(Integer userId, PasswdParam param, UserInfo userInfo) throws TipException;

    void changeAvatar(Integer userId, String avatarPath, UserInfo userInfo) throws TipException;

    boolean isExist(User user);

    boolean isExist(Integer userId);

    void changeStatus(User user) throws TipException;

    User findById(Integer userId);
    UserDetail detail(Integer userId);

}
