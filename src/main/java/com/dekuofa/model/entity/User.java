package com.dekuofa.model.entity;

import com.dekuofa.model.BaseEntity;
import com.dekuofa.model.param.UserParam;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Column;
import io.github.biezhi.anima.annotation.Ignore;
import io.github.biezhi.anima.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Collection;

/**
 * @author dekuofa <br>
 * @date 2018-08-08 <br>
 */
@Data
@Table(name = "t_user")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class User extends Model implements BaseEntity {

    @JsonIgnore
    @Ignore
    private Collection<SysRole>    sysRoles;
    @JsonIgnore
    @Ignore
    private Collection<Permission> permissions;

    private Integer id;
    @Column(name = "user_name")
    private String  username;
    @JsonIgnore // json返回值中忽略
    @JsonProperty // 解决使用 @RequestBody 时，password为空的情况
    private String  password;
    private String  nickName;
    private Integer lastLoginTime;
    private String  lastLoginIp;

    private Long    createTime;
    private Long    modifyTime;
    private Integer creatorId;
    private String  creatorName;
    private Integer modifierId;
    private String  modifierName;

    public User(UserParam userParam) {
        this.username = userParam.getUsername();
        this.password = userParam.getPassword();
        this.nickName = userParam.getPassword();
    }

}
