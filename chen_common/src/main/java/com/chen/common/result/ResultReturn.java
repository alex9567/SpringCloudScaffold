package com.chen.common.result;


public class ResultReturn<T> {
    private String code;
    private String msg;
    private T data;

    public ResultReturn() {
    }

    public ResultReturn(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultReturn(String code, String msg) {
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

    public static <T> ResultReturn<T> createResult(ResultReturnEnum resultReturnEnum, T data) {
        return new ResultReturn(resultReturnEnum.getCode(), resultReturnEnum.getMessage(), data);
    }

    public static <T> ResultReturn<T> createResult(String code, String message, T data) {
        return new ResultReturn(code, message, data);
    }

    public static <T> ResultReturn<T> success() {
        return createResult(ResultReturnEnum.SUCCESS, null);
    }

    public static <T> ResultReturn<T> success(T data) {
        return createResult(ResultReturnEnum.SUCCESS, data);
    }

    public static <T> ResultReturn<T> error() {
        return createResult(ResultReturnEnum.ERROR, null);
    }

    public static <T> ResultReturn<T> customError(ResultReturnEnum resultReturnEnum) {
        return createResult(resultReturnEnum, null);
    }

    public static <T> ResultReturn<T> customError(String code, String message) {
        return createResult(code, message, null);
    }

    public static <T> ResultReturn<T> customError(ResultReturnEnum resultReturnEnum, T data) {
        return createResult(resultReturnEnum, data);
    }

    public static <T> ResultReturn<T> customError(String code, String message, T data) {
        return createResult(code, message, data);
    }
}
