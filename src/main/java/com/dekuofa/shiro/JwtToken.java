package com.dekuofa.shiro;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author ganxiang <br>
 * 时间：2018年05月03日 17:09<br>
 * 标题：JWTToken<br>
 * 功能：token<br>
 */
@Data
@AllArgsConstructor
public class JwtToken implements AuthenticationToken {

    private String token;

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
