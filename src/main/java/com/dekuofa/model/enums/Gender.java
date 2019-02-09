package com.dekuofa.model.enums;

import io.github.biezhi.anima.annotation.EnumMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dekuofa <br>
 * @date 2018-11-06 <br>
 */
@Getter
@EnumMapping("code")
@AllArgsConstructor
public enum Gender implements BaseCodeEnum {
    MALE(1, "男"),
    FEMALE(2, "女"),
    NULL(0, "暂未设置")
    ;
    private Integer code;
    private String  desc;

}
