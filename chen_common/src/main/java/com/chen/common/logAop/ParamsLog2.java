package com.chen.common.logAop;

import java.lang.annotation.*;

/**
 * @author chen
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ParamsLog2 {

    String description() default "";

    boolean logTrace() default false;
}

