package com.dekuofa.utils;

import com.dekuofa.exception.ValidateException;
import com.dekuofa.model.param.SysRoleParam;
import org.springframework.util.StringUtils;

/**
 * @author gx <br>
 * @date 2018-08-28 <br>
 */
public class CommonValidator {

    public static void validate(SysRoleParam roleParam) {
        if (roleParam == null) {
            throw new ValidateException("角色参数不能为空");
        }
        if (StringUtils.isEmpty(roleParam.getName())) {
            throw new ValidateException("角色名不能为空");
        }
    }
}
