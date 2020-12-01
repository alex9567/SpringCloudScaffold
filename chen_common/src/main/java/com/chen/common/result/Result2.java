package com.chen.common.result;


import com.chen.common.exception.ErrorEnum;

public class Result2<T> {
    private Object code;
    private Object msg;
    private T data;

    public Result2() {
    }

    public Result2(Object code, Object msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result2(Object code, Object msg) {
        this.code = code;
        this.msg = msg;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Result2<T> createResult(ErrorEnum errorEnum, T data) {
        return new Result2(errorEnum.getCode(), errorEnum.getMessage(), data);
    }

    public static <T> Result2<T> createResult(Object code, Object message, T data) {
        return new Result2(code, message, data);
    }

    public static <T> Result2<T> success() {
        return createResult(ErrorEnum.SUCCESS, null);
    }

    public static <T> Result2<T> success(T data) {
        return createResult(ErrorEnum.SUCCESS, data);
    }

    public static <T> Result2<T> error() {
        return createResult(ErrorEnum.ERROR, null);
    }

    public static <T> Result2<T> customError(ErrorEnum errorEnum) {
        return createResult(errorEnum, null);
    }

    public static <T> Result2<T> customError(Object code, Object message) {
        return createResult(code, message, null);
    }

    public static <T> Result2<T> customError(ErrorEnum errorEnum, T data) {
        return createResult(errorEnum, data);
    }

    public static <T> Result2<T> customError(Object code, Object message, T data) {
        return createResult(code, message, data);
    }
}
