package com.dekuofa.model.response;

import lombok.Data;

import java.util.Collection;

/**
 * @author dekuofa <br>
 * @date 2018-09-07 <br>
 */
@Data
public class UserInfoResponse {
    private Integer id;
    private String  name;
    // 头像
    private String  avatar;

    private Collection<String> roles;

    public UserInfoResponse() {
    }

    public UserInfoResponse(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public UserInfoResponse name(String name) {
        this.name = name;
        return this;
    }

    public UserInfoResponse avatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public UserInfoResponse roles(Collection<String> roles) {
        this.roles = roles;
        return this;
    }

    public UserInfoResponse id(Integer id) {
        this.id = id;
        return this;
    }
}
