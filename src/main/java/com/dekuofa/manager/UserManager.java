package com.dekuofa.manager;


import com.dekuofa.model.entity.User;

/**
 * @author gx <br>
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
    void login(int userId, String ip);
}
