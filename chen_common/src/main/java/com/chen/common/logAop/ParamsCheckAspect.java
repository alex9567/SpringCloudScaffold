package com.chen.common.logAop;

import com.alibaba.fastjson.JSON;
import com.chen.common.exception.BaseException;
import com.chen.common.exception.BaseException2;
import com.chen.common.result.ResultReturn;
import com.chen.common.result.ResultReturnEnum;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Aspect
@Component
@Slf4j
@Order(110)
public class ParamsCheckAspect {

    /**
     * 以自定义注解为切点
     */
    @Pointcut("@annotation(com.chen.common.logAop.ParamsCheck)")
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
                    JSON.toJSONString(result));
            throw new BaseException2(joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName(), ResultReturnEnum.PARAM_ERROR.getCode(),JSON.toJSONString(bindingResult.getAllErrors()));
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
