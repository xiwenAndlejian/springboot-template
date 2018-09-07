package com.dekuofa.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dekuofa <br>
 * @date 2018-09-07 <br>
 */
@Getter
@AllArgsConstructor
public enum BaseStatus implements BaseCodeEnum {

    INTI(0, "init"),
    NORMAL(1, "normal"),
    BANED(8, "baned"),
    DELETED(9, "deleted")
    ;
    private Integer code;
    private String  desc;

}
