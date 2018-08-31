package com.dekuofa;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author dekuofa <br>
 * @date 2018-08-24 <br>
 */
public class InvokerTest {


    public static void test(int i) {
        System.out.println(i);
    }


    public static void main(String[] args) {
        try {
            Method method = InvokerTest.class.getMethod("test", int.class);

            if (method != null) {

                method.invoke(new InvokerTest(), new Object[]{null});
            } else {
                System.out.println("方法不存在");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
