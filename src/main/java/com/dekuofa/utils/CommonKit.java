package com.dekuofa.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author dekuofa <br>
 * @date 2018-08-15 <br>
 */
public class CommonKit {

    /**
     * 根据id列表获取用于 sql in 查询的字符串<br/>
     * eg : input [1, 2, 3, 4, 5] return 1,2,3,4,5
     * 如果传入数组为 null 或者 长度为空 则返回 ""
     */
    public static <ID> String ids2String(Collection<ID> ids) {
        if (ids == null || ids.size() == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Stream.of(ids)
                .forEach(x -> sb.append(x).append(","));
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * return first - (first ∩ second)
     */
    public static <T> Set<T> difference(T[] first, T[] second) {
        Set<T> set = Arrays.stream(second).collect(Collectors.toSet());
        return Arrays.stream(first)
                .filter(i -> !set.contains(i))
                .collect(Collectors.toSet());
    }

}
