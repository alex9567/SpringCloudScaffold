package com.chen.common.web;

public enum ResultEnum {
    SUCCESS("200","成功"),
    ERROR("500","系统异常"),
    ;
    private String code;
    private String message;

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
