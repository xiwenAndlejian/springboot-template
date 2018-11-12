package com.dekuofa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dekuofa <br>
 * @date 2018-11-10 <br>
 */
public class HeapPollutionExample {
    public static void main(String[] args) {

        List<String> stringListA = new ArrayList<>();
        List<String> stringListB = new ArrayList<>();

        ArrayBuilder.addToList(stringListA, "Seven", "Eight", "Nine");
        ArrayBuilder.addToList(stringListB, "Ten", "Eleven", "Twelve");

        List<List<String>> listOfStringLists =
                new ArrayList<>();
        ArrayBuilder.addToList(listOfStringLists,
                stringListA, stringListB);

        ArrayBuilder.faultyMethod(Arrays.asList("Hello!"), Arrays.asList("World!"));
    }
}
