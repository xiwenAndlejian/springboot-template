package com.dekuofa;

/**
 * @author dekuofa <br>
 * @date 2018-11-07 <br>
 */
public class Son extends Parent<Integer> {
    public Son(Integer data) {
        super(data);
    }

    public void setData(Integer data) {
        System.out.println("Son setData");
        super.setData(data);
    }
}
