package com.dekuofa.model.entity;

import com.dekuofa.model.BaseEntity;
import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Column;
import io.github.biezhi.anima.annotation.Ignore;
import io.github.biezhi.anima.annotation.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

/**
 * @author gx <br>
 * @date 2018-08-08 <br>
 */
@Data
@Table(name = "t_user")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class User extends Model implements BaseEntity {

    @Ignore
    private Collection<SysRole> sysRoles;
    @Ignore
    private Collection<Permission>    permissions;

    private int    id;
    @Column(name = "user_name")
    private String username;
    private String password;
    private String nickName;
    private int    lastLoginTime;
    private String lastLoginIp;

    private int    createTime;
    private int    modifyTime;
    private int    creatorId;
    private String creatorName;
    private int    modifierId;
    private String modifierName;

}
