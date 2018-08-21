package com.dekuofa.model.entity;

import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Column;
import io.github.biezhi.anima.annotation.Ignore;
import io.github.biezhi.anima.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author gx <br>
 * @date 2018-08-08 <br>
 */
@Data
@Table(name = "t_user")
@EqualsAndHashCode(callSuper = false)
public class User extends Model {

    private int id;
    @Column(name = "user_name")
    private String  username;
    private String  password;
    private String  nickName;
    private int lastLoginTime;
    private String  lastLoginIp;

    private int createTime;
    private int modifyTime;
    private int creatorId;
    private String  creatorName;
    private int modifierId;
    private String  modifierName;


    @Ignore
    private List<Role>       roles;
    @Ignore
    private List<Permission> permissions;

    public User() {
    }

}
