package com.dekuofa.model.response;

import com.dekuofa.utils.DateUtil;
import lombok.Data;

import static com.dekuofa.utils.DateUtil.newUnixMilliSecond;

/**
 * @author dekuofa <br>
 * @date 2018-08-08 <br>
 */
@Data
public class RestResponse<T> {

    /**
     * 返回的信息
     */
    private T       payload;
    /**
     * 错误代码
     */
    private int     code = 0;
    /**
     * 错误信息
     */
    private String  msg;
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 时间戳
     */
    private Long    timestamp;


    public RestResponse() {
        this.timestamp = newUnixMilliSecond();
    }

    public RestResponse(boolean success) {
        this.timestamp = newUnixMilliSecond();
        this.success = success;
    }

    public RestResponse(boolean success, T payload) {
        this.timestamp = newUnixMilliSecond();
        this.payload = payload;
        this.success = success;
    }

    public RestResponse<T> success(boolean success) {
        this.success = success;
        return this;
    }

    public RestResponse<T> payload(T payload) {
        this.payload = payload;
        return this;
    }

    public RestResponse<T> code(int code) {
        this.code = code;
        return this;
    }

    public RestResponse<T> message(String msg) {
        this.msg = msg;
        return this;
    }

    public static <T> RestResponse<T> ok() {
        return new RestResponse<T>().success(true);
    }

    public static <T> RestResponse<T> ok(T payload) {
        return new RestResponse<T>().success(true).payload(payload);
    }

    public static <T> RestResponse ok(T payload, int code) {
        return new RestResponse<T>().success(true).payload(payload).code(code);
    }

    public static <T> RestResponse<T> fail() {
        return new RestResponse<T>().success(false);
    }

    public static <T> RestResponse<T> fail(String message) {
        return new RestResponse<T>().success(false).code(1).message(message);
    }

    public static <T> RestResponse fail(int code, String message) {
        return new RestResponse<T>().success(false).message(message).code(code);
    }

}