package com.dekuofa.model.response;

import com.dekuofa.model.NormalUserInfo;
import com.dekuofa.model.entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

/**
 * @author dekuofa <br>
 * @date 2018-08-22 <br>
 */
@Data
@AllArgsConstructor
public class LoginResponse {

    private NormalUserInfo         userInfo;
    private String                 token;
    private Collection<Permission> permissions;
}
