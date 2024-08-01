package com.hjc.demo.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseVO<T> implements Serializable {
    // 状态码: 0-成功，其他-失败
    private final Integer code;
    // 返回信息
    private final String message;
    //返回值
    private final T data;
    //是否成功
    private final Boolean success;
    // 成功返回
    public static <T> ResponseVO<T> success(T data) {
        return new ResponseVO<>(data);
    }
    // 失败返回
    public static <T> ResponseVO<T> error(Integer code, String message, T data) {
        return new ResponseVO<>(code, message, data);
    }
    public ResponseVO(T data) {
        this.code = ResponseConstant.SUCCESS_CODE;
        this.message = ResponseConstant.OK;
        this.data = data;
        this.success = true;
    }
    public ResponseVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = code == ResponseConstant.SUCCESS_CODE;
    }
}
