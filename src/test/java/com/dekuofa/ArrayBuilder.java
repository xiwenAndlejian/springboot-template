package com.dekuofa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dekuofa <br>
 * @date 2018-11-10 <br>
 */
public class ArrayBuilder {

    public static <T> void addToList(List<T> listArg, T... elements) {
        for (T x : elements) {
            listArg.add(x);
        }
    }

    public static void faultyMethod(List<String>... l) {
        Object[] objectArray = l;     // Valid
        objectArray[0] = Arrays.asList(42);
        String s = l[0].get(0);       // ClassCastException thrown here
        Object[] strings = new String[2];
        strings[0] = "hi";// ok
        strings[1] = 100;
    }


}

