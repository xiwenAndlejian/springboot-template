package com.dekuofa.model.entity;

import com.dekuofa.model.BaseEntity;
import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Table;
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
@Table(name = "t_permission")
public class Permission extends Model implements BaseEntity {
    private Integer id;
    private String  name;
    private String  url;
    private Integer method;
    private Integer createTime;
    private Integer modifyTime;
    private Integer creatorId;
    private String  creatorName;
    private Integer modifierId;
    private String  modifierName;
}
