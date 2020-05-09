package com.chen.common.exception;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -4939515770813597146L;
    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误码对应的参数
     */
    private Object args;

    /**
     * 错误消息
     */
    private String message;

    public BaseException(){}

    public BaseException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseException(String module, String code, String message) {
        this.module = module;
        this.code = code;
        this.message = message;
    }

    public BaseException(String module, String code, Object args, String message) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.message = message;
    }

    public String getModule() {
        return module;
    }

    public String getCode() {
        return code;
    }

    public Object getArgs() {
        return args;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
