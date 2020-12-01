package com.chen.config.logAop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Aspect
@Component
@Slf4j
@Order(90)
public class TraceLogAspect {

    private static final String TRACE_ID = "TRACE_ID";

    /**
     * 以自定义注解为切点
     */
    @Pointcut("@annotation(com.chen.config.logAop.TraceLog)")
    public void traceLog() {
    }

    /**
     * 在切点之前织入
     */
    @Before("traceLog()")
    public void doBefore() {
        String traceId = UUID.randomUUID().toString();
        MDC.put(TRACE_ID, traceId);
    }

    /**
     * 环绕
     */
    @Around("traceLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();
        MDC.remove(TRACE_ID);
        return result;
    }


}
