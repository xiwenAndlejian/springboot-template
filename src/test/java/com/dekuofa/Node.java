package com.dekuofa;

/**
 * @author dekuofa <br>
 * @date 2018-11-10 <br>
 */
public class Node<T> {

    public T data;

    public Node(T data) {
        this.data = data;
    }

    public void setData(T data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}
