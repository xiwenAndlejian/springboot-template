package com.dekuofa.utils;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Verification;

import java.util.function.BiFunction;
import java.util.function.Function;

import static com.dekuofa.utils.JwtVerifier.init;

/**
 * @author dekuofa <br>
 * @date 2018-10-23 <br>
 */
public interface JwtRealm<T> {
    Function<String, Algorithm> getEncrypt();

    String getSecret();

    T getPayload();

    <T> BiFunction<Verification, T, JwtVerifier> getClaims();

    default JwtVerifier getVerifier() {
        return init()
                .secret(getSecret())
                .encrypt(getEncrypt())
                .payload(getPayload())
                .claims(getClaims())
                .build();
    }
}
