package com.dekuofa.utils;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * @author gx <br>
 * @date 2018-08-15 <br>
 */
public class CommonKit {

    /**
     * 根据id列表获取用于 sql in 查询的字符串<br/>
     * eg : input [1, 2, 3, 4, 5] return 1,2,3,4,5
     * 如果传入数组为 null 或者 长度为空 则返回 ""
     *
     * @param ids
     * @param <ID>
     * @return
     */
    public static  <ID> String ids2String(Collection<ID> ids) {
        if (ids == null || ids.size() == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Stream.of(ids)
                .forEach(x -> sb.append(x).append(","));
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
