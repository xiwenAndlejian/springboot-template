package com.dekuofa.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dekuofa.constant.Constants;
import com.dekuofa.exception.TipException;
import com.dekuofa.model.BaseUserInfo;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.enums.UserType;
import lombok.extern.log4j.Log4j2;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Optional;

/**
 * @author ganxiang <br>
 * 时间：2018年05月03日 15:31<br>
 * 标题：JWTUtil<br>
 * 功能：json web token 工具类<br>
 */
@Log4j2
public class JwtUtil {

    /**
     * 设置过期时间，单位毫秒
     * todo 目前token时间延长至12，需要想办法半小时过期如何处理
     */
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000;

    /**
     * token前缀
     */
    private static final String TOKEN_HEAD = Constants.TOKEN_HEAD;


    /**
     * 校验token
     *
     * @param token    令牌
     * @param userInfo 用户信息
     * @param secret   用户密码
     * @return 验证结果：true/false
     */
    public static boolean verify(String token, BaseUserInfo userInfo, String secret) {
        try {
            //根据token的信息构建解析方法
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                    .withClaim("username", userInfo.getUsername())
                    .withClaim("userId", userInfo.getUserId())
                    .withClaim("userType", userInfo.getUserType().getCode())
                    .withClaim("nickName", userInfo.getNickName())
                    .build();
            //如果验证失败会报错
            verifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException e1) {
            log.error("无法进行token编码");
//            return false;
        } catch (SignatureVerificationException e2) {
            log.error("token已过期");
        } catch (JWTVerificationException e3) {
            log.error("权限验证异常");
        }
        return false;
    }

    /**
     * 从token中获取用户名
     *
     * @param token 令牌
     * @return token中的用户名
     */
    public static Optional<BaseUserInfo> getUserInfo(String token) {
        try {
            DecodedJWT jwt      = JWT.decode(token);
            Integer    userId   = jwt.getClaim("userId").asInt();
            String     username = jwt.getClaim("username").asString();
            String     nickName = jwt.getClaim("nickName").asString();
            UserType   userType = jwt.getClaim("userType").as(UserType.class);

            return Optional.of(
                    new UserInfo(userId, username, nickName, userType));
        } catch (JWTDecodeException e) {
            log.error("jwt解析失败：" + e.getCause());
            return Optional.empty();
        }
    }

    /**
     * 生成token
     *
     * @param password 密码（用于盐加密）
     * @return token令牌，String
     */
    public static String sign(UserInfo userInfo,
                              String password) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            return
//                    TOKEN_HEAD +
                    JWT.create()
                            .withClaim("username", userInfo.getUsername())
                            .withClaim("userId", userInfo.getUserId())
                            .withClaim("userType", userInfo.getUserType().getCode())
                            .withClaim("nickName", userInfo.getNickName())
                            .withExpiresAt(date)
                            // 密码加盐
                            .sign(Algorithm.HMAC256(password));
        } catch (UnsupportedEncodingException e) {
            log.error("生成token失败：" + e.getCause());
            return null;
        }
    }

    public static String getToken(String secret) {
        if (secret == null || !secret.startsWith(TOKEN_HEAD)) {
            throw new TipException("token格式异常");
        }
        final String token = secret.substring(TOKEN_HEAD.length());
        return token;
    }

}
