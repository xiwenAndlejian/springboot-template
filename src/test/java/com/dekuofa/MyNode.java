package com.dekuofa;

/**
 * @author dekuofa <br>
 * @date 2018-11-10 <br>
 */
public class MyNode extends Node<Integer>  {

    public MyNode(Integer data) {
        super(data);
    }

    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }
}
