package com.chen.common.exception;

public enum ErrorEnum {
    SUCCESS("200","成功"),
    ERROR("500","系统异常"),
    ;
    private String code;
    private String message;

    ErrorEnum(String code, String message) {
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
