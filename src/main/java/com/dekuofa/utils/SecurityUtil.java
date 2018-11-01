package com.dekuofa.utils;

import com.dekuofa.exception.TipException;
import com.dekuofa.model.UserInfo;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.Optional;


/**
 * @author ganxiang <br>
 * 时间：2018年06月11日 10:38<br>
 * 标题：SecurityUtils<br>
 * 功能：安全工具类<br>
 */
@Log4j2
public class SecurityUtil {
    public static Optional<UserInfo> getCurrentUserInfo() {
        String token = "";
        try {
            Subject currentUser = Optional.of(SecurityUtils.getSubject())
                    .orElseThrow(() -> new TipException("用户权限模板获取失败"));
            token = Optional.of((String) currentUser.getPrincipal())
                    .orElseThrow(() -> new TipException("token校验失败"));
            return JwtUtil.getUserInfo(token);
        } catch (Exception e) {
            String msg = "token异常";
            if (e instanceof TipException) {
                msg = e.getMessage();
            }
            log.error("获取用户信息失败：{}", msg);
            log.error("token：{}", token);
            return Optional.empty();
        }
    }
}
