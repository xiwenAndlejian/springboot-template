package com.dekuofa;

import com.dekuofa.utils.BaseCodeEnumConverterFactory;
import org.sql2o.converters.Converter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 泛型测试
 *
 * @author dekuofa <br>
 * @date 2018-11-07 <br>
 */
public class GenericTest {

    public static void main(String[] args) {
        Object[] objects = new Object[]{
//                BaseCodeEnumConverterFactory.newCodeConverter(),
                BaseCodeEnumConverterFactory.newBaseCodeConverter(),
                new Node<>(5)
        };
        for (Object o : objects) {
            Type[]   types  = o.getClass().getGenericInterfaces();
            Type[]   params = ((ParameterizedType) types[0]).getActualTypeArguments();
            Class<?> type   = (Class) params[0];
            System.out.println(type.getName());
        }
    }
}


