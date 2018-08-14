package com.shuda.config;

import com.shuda.model.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author gx <br>
 * @date 2018-08-08 <br>
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public RestResponse<?> exceptionHandler(HttpServletRequest request, Exception e) {
        return RestResponse.fail(e.getMessage());
    }

}
