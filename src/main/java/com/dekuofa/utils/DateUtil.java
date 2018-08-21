package com.dekuofa.utils;

import java.time.Instant;

/**
 * @author gx <br>
 * @date 2018-08-08 <br>
 */
public class DateUtil {
    /**
     * 获取当前的unix类型时间戳
     *
     * @return int类型的时间戳
     */
    public static Integer newUnix() {
        return (int) Instant.now().getEpochSecond();
    }
}
