package com.shuda.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ganxiang <br>
 * 时间：2018年04月25日 11:02<br>
 * 标题：SysPermission<br>
 * 功能：系统权限<br>
 */
@Data
@NoArgsConstructor
public class SysPermission implements Serializable {
    private Integer id;
    private String  url;
    private String  name;
}
