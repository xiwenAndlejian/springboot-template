package com.dekuofa.model.entity;

import com.dekuofa.model.BaseEntity;
import com.dekuofa.model.param.SysRoleParam;
import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Table;
import lombok.*;


/**
 * @author dekuofa <br>
 * @date 2018-08-14 <br>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Table(name = "t_role")
public class SysRole extends Model implements BaseEntity {
    private Integer id;
    private String  name;
    private String  desc;
    private Integer createTime;
    private Integer modifyTime;
    private Integer creatorId;
    private String  creatorName;
    private Integer modifierId;
    private String  modifierName;

    public SysRole(String name) {
        this.name = name;
    }

    public SysRole(SysRoleParam param) {
        this(param.getName());
    }


}
