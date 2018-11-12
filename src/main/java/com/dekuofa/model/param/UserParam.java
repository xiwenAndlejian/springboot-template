package com.dekuofa.model.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;


/**
 * @author dekuofa <br>
 * @date 2018-08-22 <br>
 */
@Data
public class UserParam {

    @Length(min = 5, max = 30, message = "用户名不符合规范：长度{min}-{max}")
    private String username;
    @Length(min = 8, max = 16, message = "密码不符合规范：长度 {min}-{max}")
    private String password;
    @Length(min = 2, max = 30, message = "昵称不符合规范：长度 {min}-{max}")
    private String nickName;
    // todo 正则校验路径参数（应该区分系统）
    private String avatar;
}
