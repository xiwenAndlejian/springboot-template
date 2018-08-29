package com.dekuofa.model.relation;

import io.github.biezhi.anima.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author gx <br>
 * @date 2018-08-28 <br>
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class RolePermission extends Model {
    private Integer roleId;
    private Integer permissionId;
}
