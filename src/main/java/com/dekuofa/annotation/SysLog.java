package com.dekuofa.annotation;

import java.lang.annotation.*;

/**
 * @author dekuofa <br>
 * @date 2018-08-17 <br>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String action();
}
