package com.chen.common.logAop;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



@Aspect
@Component
@Slf4j
@Order(100)
public class ParamsLogAspect {

    /**
     * 以自定义注解为切点
     */
    @Pointcut("@annotation(com.chen.common.logAop.ParamsLog)")
    public void paramsLog() {
    }

    /**
     * 在切点之前织入
     *
     * @param joinPoint
     */
    @Before("paramsLog()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("ClassMethod:{}.{},RequestArgs:{}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                new Gson().toJson(joinPoint.getArgs()));
    }


    /**
     * 环绕
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("paramsLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        //最终需要的日志格式
        log.info("ClassMethod:{}.{},ResponseArgs:{},Time-Consuming:{}ms", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                proceedingJoinPoint.getSignature().getName(),
                new Gson().toJson(result),
                System.currentTimeMillis() - startTime);
        return result;
    }

}
