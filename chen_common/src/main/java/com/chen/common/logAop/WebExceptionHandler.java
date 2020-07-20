package com.chen.common.logAop;

import com.chen.common.exception.BaseException;
import com.chen.common.exception.BaseException2;
import com.chen.common.result.ResultReturn;
import com.chen.common.result.ResultReturn2;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@ControllerAdvice
@Slf4j
@ResponseBody
public class WebExceptionHandler {


    /**
     * 处理自定义的业务异常
     * 用于处理@valid 异常抛出的问题，这个用于处理只有@valid的异常
     * 这个方法是在serviceimpl中捕获的
     * HandlerMethod method这个参数可以获得一些方法的额外信息
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultReturn2 handleValidException2(MethodArgumentNotValidException e) {
        //日志记录错误信息
        log.info(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        //将错误信息返回给前台
        return ResultReturn2.customError(300, Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }
    /**
     * 处理自定义的业务异常
     * 用于处理@valid 异常抛出的问题，这个用于处理只有@valid的异常
     * 这个方法是在controller中捕获的
     * HandlerMethod method这个参数可以获得一些方法的额外信息
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public ResultReturn2 handleValidException(BindException e) {
        //日志记录错误信息
        log.info(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        //将错误信息返回给前台
        return ResultReturn2.customError(300, Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }


    /**
     * 处理自定义的业务异常
     * 用于处理@valid 异常抛出的问题，这个用于处理@valid 和BindingResult 组合aop导致抛出的自定义异常
     * HandlerMethod method这个参数可以获得一些方法的额外信息
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BaseException2.class)
    public ResultReturn2 bizExceptionHandler(BaseException2 e) {
        log.info("valid error,method:{},errorCode:{},errorMessage:{}", e.getModule(), e.getCode(), new Gson().toJson(e.getmsg()));
        return ResultReturn2.customError(e.getCode(), e.getmsg());
    }

    /**
     * 处理自定义的业务异常
     * HandlerMethod method这个参数可以获得一些方法的额外信息
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    public ResultReturn bizExceptionHandler(BaseException e) {
        log.info("basic error,method:{},errorCode:{},errorMessage:{}", e.getModule(), e.getCode(), e.getMessage());
        return ResultReturn.customError(e.getCode(), e.getMessage());
    }

    /**
     * 处理其他异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultReturn exceptionHandler(Exception e) {
        log.info("wow,unknow erroro happen", e);
        return ResultReturn.error();
    }
}


