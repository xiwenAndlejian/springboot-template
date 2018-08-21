package com.dekuofa.config;

import com.dekuofa.annotation.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author gx <br>
 * @date 2018-08-17 <br>
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    @Pointcut(value = "execution(@com.dekuofa.annotation.SysLog * * (..))")
    public void log() {}

    @AfterReturning(pointcut = "log()")
    public void success(JoinPoint point) {
        log.info("{}: success", getAction(point));
    }

    @AfterThrowing(pointcut = "log()", throwing = "e")
    public void error(JoinPoint point, Exception e) {
        log.info("{}: error cause: {}", getAction(point), e.getMessage());
    }

    private String getAction(JoinPoint point) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method          method          = methodSignature.getMethod();
        SysLog          sysLog          = method.getAnnotation(SysLog.class);
        return sysLog.action();
    }
}
