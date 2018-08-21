package com.dekuofa.model.entity;

import io.github.biezhi.anima.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author gx <br>
 * @date 2018-08-14 <br>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Permission extends Model {
    private int    id;
    private String name;
    private String url;
    private int    method;
    private int    createTime;
    private int    modifyTime;
    private int    creatorId;
    private String creatorName;
    private int    modifierId;
    private String modifierName;
}
