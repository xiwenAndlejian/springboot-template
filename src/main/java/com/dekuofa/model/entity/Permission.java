package com.dekuofa.model.entity;

import com.dekuofa.model.BaseEntity;
import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 权限信息暂时不允许修改&新增
 *
 * @author dekuofa <br>
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
    private String  method;
}
