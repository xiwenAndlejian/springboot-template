package com.shuda.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author ganxiang <br>
 * 时间：2018年04月25日 10:58<br>
 * 标题：SysRole<br>
 * 功能：系统角色<br>
 */
@Data
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysRole implements Serializable {

    private List<SysPermission> permissions;

    private Integer id;
    /**
     * 角色名称
     */
    private String  roleName;
    /**
     * 角色类型
     */
    private String  desc;
    private Integer createTime;
    private Integer modifyTime;
    private Integer creatorId;
    private Integer status;
}
