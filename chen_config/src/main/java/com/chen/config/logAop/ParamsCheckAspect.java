package com.chen.config.logAop;

import com.chen.common.exception.BaseException2;
import com.chen.common.exception.ErrorEnum;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;


@Aspect
@Component
@Slf4j
@Order(110)
public class ParamsCheckAspect {

    /**
     * 以自定义注解为切点
     */
    @Pointcut("@annotation(com.chen.config.logAop.ParamsCheck)")
    public void paramsCheck() {
    }

    /**
     * 在切点之前织入
     *
     * @param joinPoint
     */
    @Before("paramsCheck()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] objects = joinPoint.getArgs();
        List<Object> result = new ArrayList<>();
        BindingResult bindingResult = null;
        for (Object o : objects) {
            if (o instanceof BindingResult) {
                bindingResult = (BindingResult) o;
                continue;
            }
            result.add(o);
        }

        if (bindingResult != null && bindingResult.hasErrors()) {
            log.info("ClassMethod:{}.{}.paramsError,RequestArgs:{}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    new Gson().toJson(result));
            throw new BaseException2(joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName(), ErrorEnum.PARAM_ERROR.getCode(),bindingResult.getAllErrors());
        }
        log.info("ClassMethod:{}.{},RequestArgs:{}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                new Gson().toJson(result));
    }

    /**
     * 环绕
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("paramsCheck()")
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
