package com.dekuofa.model;

import com.dekuofa.model.enums.UserType;

/**
 * fixme 自定义handler处理接口，
 *
 * @author gx <br>
 * @date 2018-08-21 <br>
 */
public interface BaseUserInfo {
    String getUsername();

    String getNickName();

    UserType getUserType();

    int getUserId();

    boolean isEmpty();

    boolean isCurrentUser(int userId);
}
