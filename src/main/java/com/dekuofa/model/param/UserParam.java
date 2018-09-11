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
    // todo 正则匹配更复杂的校验至少一个大写字母一个小写字母和一个特殊字符（三选2）
    @Length(min = 8, max = 16, message = "密码不符合规范：长度 {min}-{max}")
    private String password;
    @Length(min = 2, max = 30, message = "昵称不符合规范：长度 {min}-{max}")
    private String nickName;
    @URL
    private String avatar;
}
