package com.dekuofa.constant;

/**
 * @author ganxiang <br>
 * 时间：2018年06月04日 11:07<br>
 * 标题：Constant<br>
 * 功能：常量<br>
 */
public class Constants {
    /**
     * MB单位
     */
    public static final Long SIZE_OF_MB = 1024 * 1024L;

    /**
     * 富文本最大长度5000
     */
    public static final int MAX_OF_CONTENT   = 5000;
    /**
     * 最大用户名长度
     */
    public static final int MAX_OF_USERNAME  = 30;
    /**
     * 最大昵称长度
     */
    public static final int MAX_OF_NICK_NAME = 30;
    /**
     * 最大密码长度
     */
    public static final int MAX_OF_PASSWD    = 64;

    /**
     * 默认报告的地区id，用于计算指标报告得分
     */
    public static final int DEFAULT_REPORT_LOCATION_ID = -1;
    /**
     * 默认分页页数大小
     */
    public static final int DEFAULT_PAGE_SIZE          = 10;
    /**
     * 默认分页，当前页数
     */
    public static final int DEFAULT_PAGE_NUM           = 1;

    public static final String DEFAULT_FILE_TYPE = ".file";

    /**
     * token key
     */
    public static final String TOKEN_KEY = "Authorization";

    /**
     * 手机号校验正则biaodashu
     */
    public static final String REGEX_OF_MOBILE = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
}
