package com.chen.common.exception;

import com.chen.common.result.ResultReturn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
@ResponseBody
public class GlobalExceptionHandler {
    /**
     * 处理自定义的业务异常
     * HandlerMethod method这个参数可以获得一些方法的额外信息
     * @param e
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    public ResultReturn bizExceptionHandler(BaseException e) {
        log.info("basic error,method:{},errorMessage:{}", e.getModule(),e.getMessage());
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


