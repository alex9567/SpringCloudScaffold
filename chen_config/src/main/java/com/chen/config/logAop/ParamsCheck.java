package com.chen.config.logAop;

import java.lang.annotation.*;

/**
 * @author chen
 * 用于处理一般的controller并且用到了@Valid和BindingResult做参数校验的app
 * 如果正常出去参数，就打印出入参数，如果发生异常，就只打印入参，和异常message,并且抛出异常
 * jdcksong和fastjson在把BindingResult json输出的时候会报错，但是google 的Gson不会出错
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ParamsCheck {

    String description() default "";

}

