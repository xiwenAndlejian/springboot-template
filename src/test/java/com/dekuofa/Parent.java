package com.dekuofa;

/**
 * @author dekuofa <br>
 * @date 2018-11-07 <br>
 */
public class Parent<T> {
    T data;

    public Parent(T data) {

        this.data = data;
    }

    public void setData(T data) {
        System.out.println("Parent setData");
        this.data = data;
    }
}
