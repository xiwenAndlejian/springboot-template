package com.dekuofa.utils;

import com.dekuofa.model.common.BeanMethod;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * @author gx <br>
 * @date 2018-08-21 <br>
 */
public class BeanKit {

    public static <T, R> void injectProperty(BeanMethod<T, R> bean, Object object, T param) {
        Class<?> clazz = bean.getClazz();
        try {
            Method method = clazz.getDeclaredMethod(bean.getMethod(), bean.getPropertyType());
            method.setAccessible(true);
            Function<T, R> action = bean.getFunction();
            method.invoke(object, action.apply(param));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.out.println("更新修改信息失败");
        }
    }

}
