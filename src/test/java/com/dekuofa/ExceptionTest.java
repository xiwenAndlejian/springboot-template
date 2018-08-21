package com.dekuofa;


/**
 * @author gx <br>
 * @date 2018-08-17 <br>
 */
public class ExceptionTest {

    public static void main(String[] args) {
        try {
            a();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getCause());
        }
    }

    public static void a() throws Exception {
        b();
    }

    public static void b() throws Exception {
        throw new Exception("exception");
    }
}
