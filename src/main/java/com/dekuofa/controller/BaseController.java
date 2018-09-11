package com.dekuofa.controller;

import com.dekuofa.constant.Constants;
import com.dekuofa.exception.TipException;

/**
 * @author dekuofa <br>
 * @date 2018-09-10 <br>
 */
public interface BaseController {

    default String getErrorMessage(Exception e) {
        String message = Constants.ERROR_MESSAGE;
        if (e instanceof TipException) {
            message = e.getMessage();
        } else {
            // 排查具体错误
            e.printStackTrace();
        }
        return message;
    }
}
