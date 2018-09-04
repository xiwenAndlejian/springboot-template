package com.dekuofa.constant;

/**
 * @author ganxiang <br>
 * 时间：2018年06月04日 11:07<br>
 * 标题：Constant<br>
 * 功能：常量<br>
 */
public interface Constants {
    /**
     * MB单位
     */
    Long SIZE_OF_MB = 1024 * 1024L;

    /**
     * 富文本最大长度5000
     */
    int MAX_OF_CONTENT   = 5000;
    /**
     * 最大用户名长度
     */
    int MAX_OF_USERNAME  = 30;
    /**
     * 最大昵称长度
     */
    int MAX_OF_NICK_NAME = 30;
    /**
     * 最大密码长度
     */
    int MAX_OF_PASSWD    = 64;

    /**
     * 默认分页页数大小
     */
    int DEFAULT_PAGE_SIZE          = 10;
    /**
     * 默认分页，当前页数
     */
    int DEFAULT_PAGE_NUM           = 1;
    int FIRST_PAGE_NUM             = 1;
    /**
     * 默认分页页数大小
     */
    int DEFAULT_MAX_PAGE_SIZE      = 500;

    String DEFAULT_FILE_TYPE = ".file";

    /**
     * token key
     */
    String TOKEN_KEY = "Authorization";

    /**
     * 手机号校验正则biaodashu
     */
    String REGEX_OF_MOBILE = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
}
