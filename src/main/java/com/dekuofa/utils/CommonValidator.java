package com.dekuofa.utils;

import com.dekuofa.constant.Constants;
import com.dekuofa.exception.TipException;
import com.dekuofa.exception.ValidateException;
import com.dekuofa.model.param.PasswdParam;
import com.dekuofa.model.param.SysRoleParam;
import com.dekuofa.model.param.UserParam;
import org.springframework.util.StringUtils;

import static com.dekuofa.constant.Constants.*;

/**
 * @author dekuofa <br>
 * @date 2018-08-28 <br>
 */
public class CommonValidator {

    public static void validate(SysRoleParam roleParam) throws TipException {
        if (roleParam == null) {
            throw new ValidateException("角色参数不能为空");
        }
        if (StringUtils.isEmpty(roleParam.getName())) {
            throw new ValidateException("角色名不能为空");
        }
    }

    public static void validate(PasswdParam param) throws TipException {
        String oldPasswd = param.getOldPasswd();
        String newPasswd = param.getNewPasswd();
        if (StringUtils.isEmpty(oldPasswd)) {
            throw new TipException("旧密码不能为空");
        }
        if (StringUtils.isEmpty(newPasswd)) {
            throw new TipException("新密码不能为空");
        }

        // todo 条件校验 1.包含小写字母、特殊符号、数字、大写字母其中三种，2.最小长度&最大长度
        if (newPasswd.length() < Constants.MIN_OF_PASSWD) {
            throw new TipException("密码长度不能小于" + Constants.MIN_OF_PASSWD + "位");
        }

        if (newPasswd.length() > Constants.MAX_OF_PASSWD) {
            throw new TipException("密码长度不能大于" + Constants.MIN_OF_PASSWD + "位");
        }

    }

    public static void validate(UserParam param) {
        String    username = param.getUsername();
        String    nickName = param.getNickName();
        String[] roles    = param.getRoles();

        if (StringUtils.isEmpty(username)) {
            throw new TipException("用户名不能为空");
        }
        if (StringUtils.isEmpty(nickName)) {
            throw new TipException("昵称不能为空");
        }
        if (roles.length < 1) {
            throw new TipException("至少为用户分配一个角色");
        }

        if (username.length() > MAX_OF_USERNAME || username.length() < MIN_OF_USERNAME) {
            throw new TipException("用户名长度：" + MIN_OF_USERNAME + " ~ " + MAX_OF_USERNAME);
        }
        if (nickName.length() > MAX_OF_NICK_NAME || nickName.length() < MIN_OF_NICK_NAME) {
            throw new TipException("昵称长度：" + MIN_OF_NICK_NAME + " ~ " + MAX_OF_NICK_NAME);
        }
    }
}
