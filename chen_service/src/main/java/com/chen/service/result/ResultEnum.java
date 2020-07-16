package com.chen.service.result;

public enum ResultEnum {
    SUCCESS("200","成功"),
    PARAM_ERROR("300","传参错误"),
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
