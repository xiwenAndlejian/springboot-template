package com.dekuofa.config;

import com.dekuofa.model.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局异常处理
 *
 * @author gx <br>
 * @date 2018-08-08 <br>
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResponse<?> validatorHandler(MethodArgumentNotValidException e) {
        Map<String, String> errorMsg = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errorMsg.put(error.getField(), error.getDefaultMessage());
        }
        return RestResponse.fail("参数异常").payload(errorMsg);
    }

    @ExceptionHandler(Exception.class)
    public RestResponse<?> exceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return RestResponse.fail(e.getMessage());
    }

}
