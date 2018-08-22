package com.dekuofa.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ganxiang <br>
 * 时间：2018年04月24日 09:36<br>
 * 标题：TipException<br>
 * 功能：提示exception<br>
 */
@Getter
@Setter
public class TipException extends RuntimeException {


    public TipException() {
    }

    public TipException(String message) {
        super(message);
    }

    public TipException(String message, Throwable cause) {
        super(message, cause);
    }

    public TipException(Throwable cause) {
        super(cause);
    }

}
