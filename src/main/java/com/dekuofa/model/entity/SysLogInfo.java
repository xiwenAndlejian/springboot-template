package com.dekuofa.model.entity;

import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Column;
import io.github.biezhi.anima.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author dekuofa <br>
 * @date 2018-08-29 <br>
 */
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_log")
@Data
public class SysLogInfo extends Model {

    private Integer id;
    private Integer createTime;
    private Integer userId;
    @Column(name = "user_name")
    private String  username;
    private String  action;
}
