package com.dekuofa.exception;

/**
 * @author gx <br>
 * @date 2018-08-28 <br>
 */
public class ValidateException extends RuntimeException {
    public ValidateException() {
    }

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateException(Throwable cause) {
        super(cause);
    }
}
