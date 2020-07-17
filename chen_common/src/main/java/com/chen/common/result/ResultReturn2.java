package com.chen.common.result;


public class ResultReturn2<T> {
    private Object code;
    private Object msg;
    private T data;

    public ResultReturn2() {
    }

    public ResultReturn2(Object code, Object msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultReturn2(Object code, Object msg) {
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

    public static <T> ResultReturn2<T> createResult(ResultReturnEnum resultReturnEnum, T data) {
        return new ResultReturn2(resultReturnEnum.getCode(), resultReturnEnum.getMessage(), data);
    }

    public static <T> ResultReturn2<T> createResult(Object code, Object message, T data) {
        return new ResultReturn2(code, message, data);
    }

    public static <T> ResultReturn2<T> success() {
        return createResult(ResultReturnEnum.SUCCESS, null);
    }

    public static <T> ResultReturn2<T> success(T data) {
        return createResult(ResultReturnEnum.SUCCESS, data);
    }

    public static <T> ResultReturn2<T> error() {
        return createResult(ResultReturnEnum.ERROR, null);
    }

    public static <T> ResultReturn2<T> customError(ResultReturnEnum resultReturnEnum) {
        return createResult(resultReturnEnum, null);
    }

    public static <T> ResultReturn2<T> customError(Object code, Object message) {
        return createResult(code, message, null);
    }

    public static <T> ResultReturn2<T> customError(ResultReturnEnum resultReturnEnum, T data) {
        return createResult(resultReturnEnum, data);
    }

    public static <T> ResultReturn2<T> customError(Object code, Object message, T data) {
        return createResult(code, message, data);
    }
}
