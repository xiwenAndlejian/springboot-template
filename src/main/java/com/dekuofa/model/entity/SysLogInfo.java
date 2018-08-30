package com.dekuofa.model.entity;

import com.dekuofa.model.UserInfo;
import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Column;
import io.github.biezhi.anima.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author dekuofa <br>
 * @date 2018-08-29 <br>
 */
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_log")
@Data
public class SysLogInfo extends Model {

    private Integer id;
    private Long    createTime;
    private Integer userId;
    @Column(name = "user_name")
    private String  username;
    private String  action;
    private boolean success;
    private String  message;
    private String  params;

    public SysLogInfo(boolean success) {
        this.success = success;
    }

    public SysLogInfo createTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public SysLogInfo username(String username) {
        this.username = username;
        return this;
    }

    public SysLogInfo action(String action) {
        this.action = action;
        return this;
    }

    public SysLogInfo success(boolean success) {
        this.success = success;
        return this;
    }

    public SysLogInfo message(String message) {
        this.message = message;
        return this;
    }

    public SysLogInfo params(String params) {
        this.params = params;
        return this;
    }

    public SysLogInfo userInfo(UserInfo userInfo) {
        this.username = userInfo.getNickName();
        this.userId = userInfo.getUserId();
        return this;
    }

    public static SysLogInfo ok() {
        return new SysLogInfo(true);
    }

    public static SysLogInfo ok(String message) {
        return ok().message(message);
    }

    public static SysLogInfo ok(String message, String params) {
        return ok(message).params(params);
    }

    public static SysLogInfo fail() {
        return new SysLogInfo(false);
    }

    public static SysLogInfo fail(String message) {
        return fail().message(message);
    }

    public static SysLogInfo fail(String message, String params) {
        return fail(message).params(params);
    }
}
