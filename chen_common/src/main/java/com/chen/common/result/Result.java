package com.chen.common.result;


import com.chen.common.exception.ErrorEnum;

public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public Result() {
    }

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Result<T> createResult(ErrorEnum errorEnum, T data) {
        return new Result(errorEnum.getCode(), errorEnum.getMessage(), data);
    }

    public static <T> Result<T> createResult(String code, String message, T data) {
        return new Result(code, message, data);
    }

    public static <T> Result<T> success() {
        return createResult(ErrorEnum.SUCCESS, null);
    }

    public static <T> Result<T> success(T data) {
        return createResult(ErrorEnum.SUCCESS, data);
    }

    public static <T> Result<T> error() {
        return createResult(ErrorEnum.ERROR, null);
    }

    public static <T> Result<T> customError(ErrorEnum errorEnum) {
        return createResult(errorEnum, null);
    }

    public static <T> Result<T> customError(String code, String message) {
        return createResult(code, message, null);
    }

    public static <T> Result<T> customError(ErrorEnum errorEnum, T data) {
        return createResult(errorEnum, data);
    }

    public static <T> Result<T> customError(String code, String message, T data) {
        return createResult(code, message, data);
    }
}
