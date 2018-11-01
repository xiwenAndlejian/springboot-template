package com.dekuofa.config;

import com.dekuofa.annotation.SysLog;
import com.dekuofa.manager.LogManager;
import com.dekuofa.model.NormalUserInfo;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.entity.SysLogInfo;
import com.dekuofa.model.response.RestResponse;
import com.dekuofa.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author dekuofa <br>
 * @date 2018-08-17 <br>
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    private LogManager logManager;

    @Autowired
    public LogAspect(LogManager logManager) {
        this.logManager = logManager;
    }

    @Pointcut(value = "execution(@com.dekuofa.annotation.SysLog * * (..))")
    public void log() {}

    @AfterReturning(pointcut = "log()", returning = "returning", argNames = "point,returning")
    public void normal(JoinPoint point, Object returning) {
        Method   method   = ((MethodSignature) point.getSignature()).getMethod();
        UserInfo userInfo = getUserInfo(point);
        if (userInfo != null && !userInfo.isEmpty()) {
            log.info("当前用户:{}", userInfo.getNickName());
            String action = getAction(method);
            SysLogInfo logInfo = new SysLogInfo(isSuccess(returning));
            logInfo.userInfo(userInfo).action(action)
                    .createTime(DateUtil.newUnixMilliSecond());
            // todo 保存异常的参数
            logManager.save(logInfo);
        }
        log.info("{}: normal", getAction(method));
    }

    @AfterThrowing(pointcut = "log()", throwing = "e")
    public void exception(JoinPoint point, Exception e) {
        Method         method   = ((MethodSignature) point.getSignature()).getMethod();
        UserInfo userInfo = getUserInfo(point);
        if (userInfo != null && !userInfo.isEmpty()) {
            log.info("当前用户:{}", userInfo.getNickName());
            String action = getAction(method);
            SysLogInfo logInfo = SysLogInfo.fail().userInfo(userInfo)
                    .action(action)
                    .createTime(DateUtil.newUnixMilliSecond());
            // todo 保存异常的参数
            logManager.save(logInfo);

        }
        log.info("{}: exception cause: {}", getAction(method), e.getMessage());
    }

    private boolean isSuccess(Object returning) {
        if (returning instanceof RestResponse) {
            return ((RestResponse) returning).isSuccess();
        }
        return false;
    }

    private NormalUserInfo getUserInfo(JoinPoint point) {
        Optional<Object> first = Stream.of(point.getArgs())
                .filter(param -> param instanceof NormalUserInfo)
                .findFirst();
        return (NormalUserInfo) first.orElse(null);
    }

    private String getAction(Method method) {
        SysLog          sysLog          = method.getAnnotation(SysLog.class);
        return sysLog.action();
    }
}
