package com.dekuofa.utils;

import com.dekuofa.constant.Constants;
import com.dekuofa.exception.TipException;
import com.dekuofa.exception.ValidateException;
import com.dekuofa.model.param.PasswdParam;
import com.dekuofa.model.param.SysRoleParam;
import com.dekuofa.model.response.RestResponse;
import org.springframework.util.StringUtils;

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
}
