package com.dekuofa.model.entity;

import io.github.biezhi.anima.annotation.Column;
import io.github.biezhi.anima.annotation.Table;
import lombok.Data;

/**
 * @author gx <br>
 * @date 2018-08-29 <br>
 */
@Table(name = "t_log")
@Data
public class SysLog {
    private Integer id;
    private Integer createTime;
    private Integer userId;
    @Column(name = "user_name")
    private String username;
    private String action;
}
