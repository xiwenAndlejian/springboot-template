package com.dekuofa.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author dekuofa <br>
 * @date 2018-08-08 <br>
 */
public class DateUtil {
    /**
     * 获取当前的unix时间戳，单位秒
     *
     */
    public static Integer newUnix() {
        return (int) Instant.now().getEpochSecond();
    }

    /**
     * 获取当前unix时间戳，单位毫秒
     */
    public static Long newUnixMilliSecond() {
        return Instant.now().toEpochMilli();
    }

    /**
     * 根据日期格式获取当前日期
     *
     * @param pattern 日期格式
     * @return 根据传入的pattern 得到当前日期
     */
    public static String newDate(String pattern) {
        return new SimpleDateFormat(pattern).format(LocalDate.now());
    }

    /**
     * 获取当前日期
     *
     * @return 格式：yyyyMMdd
     */
    public static String newDate() {
        return newDate("yyyyMMdd");
    }
}
