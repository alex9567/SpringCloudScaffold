package com.chen.common.logAop;

import java.lang.annotation.*;

/**
 * @author chen
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WebLog {

    String description() default "";

}

