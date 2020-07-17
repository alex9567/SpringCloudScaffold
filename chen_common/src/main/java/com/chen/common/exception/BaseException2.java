package com.chen.common.exception;

public class BaseException2 extends RuntimeException {
    private static final long serialVersionUID = -4939515770813597146L;
    /**
     * 所属模块
     */
    private Object module;

    /**
     * 错误码
     */
    private Object code;

    /**
     * 错误码对应的参数
     */
    private Object args;

    /**
     * 错误消息
     */
    private Object msg;

    public BaseException2(){}

    public BaseException2(Object code, Object msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseException2(Object module, Object code, Object msg) {
        this.module = module;
        this.code = code;
        this.msg = msg;
    }

    public BaseException2(Object module, Object code, Object args, Object msg) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.msg = msg;
    }

    public Object getModule() {
        return module;
    }

    public Object getCode() {
        return code;
    }

    public Object getArgs() {
        return args;
    }

    public Object getmsg() {
        return msg;
    }
}
