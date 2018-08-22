package com.dekuofa.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ganxiang <br>
 * 时间：2018年06月11日 13:38<br>
 * 标题：CreatorType<br>
 * 功能：创建者枚举<br>
 */
@Getter
@AllArgsConstructor
public enum UserType implements BaseCodeEnum {

    /**
     * 后台用户
     */
    ADMIN(0, "后台用户"),
    /**
     * 前台用户
     */
    USER(1, "前台用户");

    private Integer code;
    private String  desc;

    public static UserType getByCode(Integer code) {
        UserType[] values = UserType.values();
        for (UserType type : values) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}
