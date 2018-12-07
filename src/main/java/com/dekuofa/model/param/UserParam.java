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

    private String username;
//    @Length(min = 8, max = 16, message = "密码不符合规范：长度 {min}-{max}")
//    private String password;
    private String nickName;
    private String[] roles;

}
