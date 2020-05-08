package com.chen.common.exception;

import com.alibaba.fastjson.JSON;
import com.chen.common.web.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

@ControllerAdvice
@Slf4j
@ResponseBody
public class GlobalExceptionHandler {
    /**
     * 处理自定义的业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    public Result bizExceptionHandler(BaseException e) {
        log.info("basic error,method:{},params:{}", e);
        return Result.customError(e.getCode(), e.getDefaultMessage());
    }

    /**
     * 处理其他异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e) {
        log.info("wow,unknow error,message:{}", e);
        return Result.error();
    }
}


