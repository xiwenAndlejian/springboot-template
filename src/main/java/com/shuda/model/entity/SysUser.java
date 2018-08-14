package com.shuda.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shuda.constant.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

/**
 * @author ganxiang <br>
 * 时间：2018年04月25日 10:49<br>
 * 标题：SysUser<br>
 * 功能：系统用户：后台用户<br>
 */
@Data
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysUser implements Serializable {


    static final long serialVersionUID = 1L;

    private SysRole role;

    private List<SysPermission> permissions;

    private Integer    id;
    /**
     * 用户名登陆用，长度最长64
     */
    private String     username;
    /**
     * 密码
     */
    @JsonIgnore
    private String     password;
    /**
     * 创建时间
     */
    private Integer    createTime;
    /**
     * 修改时间
     */
    private Integer    modifyTime;
    private Integer    creatorId;
    private String     creatorName;
    private Integer    modifierId;
    private String     modifierName;
    /**
     * 最近一次登陆时间
     */
    private Integer    lastLoginTime;
    @Pattern(regexp = Constants.REGEX_OF_MOBILE, message = "手机号码格式异常")
    private String     mobile;
    @NotEmpty(message = "昵称不能为空")
    @Length(max = 30, message = "昵称长度范围 %d")
    private String     nickName;


}
