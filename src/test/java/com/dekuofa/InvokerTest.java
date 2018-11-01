package com.dekuofa;

import com.dekuofa.utils.DateUtil;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Test
    public void test() {
        grayCode(1);
//        System.out.println(DateUtil.newDate());
    }

    public List<Integer> grayCode(int n) {

        int num = 0;
        List<Integer> result = new ArrayList<Integer>(){{
            add(0);
        }};
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j > 0; j--) {
                num = num ^ (1 << j);
                result.add(num);
            }
        }
        return result;
    }
}
