package com.chen.config.logAop;

import java.lang.annotation.*;

/**
 * @author chen
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ParamsLog {

    String description() default "";

}

