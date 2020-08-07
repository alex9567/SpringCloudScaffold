package com.chen.common.logAop;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Aspect
@Component
@Slf4j
@Order(100)
public class ParamsLog2Aspect {

    private static final String TRACE_ID = "TRACE_ID";

    /**
     * 以自定义注解为切点
     */
    @Pointcut("@annotation(com.chen.common.logAop.ParamsLog2)")
    public void paramsLog2() {
    }

    /**
     * 在切点之前织入
     * 注意，如果使用Gson可以直接打印所有，但是使用jackson和fastjson都不行。
     *
     * @param joinPoint
     */
    @Before("paramsLog2()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        ParamsLog2 paramsLog = getParamsLog(joinPoint);
        if(paramsLog.logTrace()){
            String traceId = UUID.randomUUID().toString();
            MDC.put(TRACE_ID, traceId);
        }
        Object[] objects = joinPoint.getArgs();
        List<Object> result = Arrays.stream(objects).filter(e->!(e instanceof BindingResult)).collect(Collectors.toList());
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
    @Around("paramsLog2()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        ParamsLog2 paramsLog = getParamsLog(proceedingJoinPoint);
        if(paramsLog.logTrace()){
            MDC.clear();
        }
        Object result = proceedingJoinPoint.proceed();
        //最终需要的日志格式
        log.info("ClassMethod:{}.{},ResponseArgs:{},Time-Consuming:{}ms", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                proceedingJoinPoint.getSignature().getName(),
                new Gson().toJson(result),
                System.currentTimeMillis() - startTime);
        return result;
    }

    /**
     * 获得paramLog
     * @param point
     * @return
     * @throws Exception
     */
    private ParamsLog2 getParamsLog(ProceedingJoinPoint point){
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        ParamsLog2 paramsLog = method.getAnnotation(ParamsLog2.class);
        return paramsLog;
    }

    /**
     * 获得paramLog
     * @param joinPoint
     * @return
     * @throws Exception
     */
    public ParamsLog2 getParamsLog(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ParamsLog2 paramsLog = method.getAnnotation(ParamsLog2.class);
        return paramsLog;
    }
    private ParamsLog2 getParamsLog2(ProceedingJoinPoint point) throws NoSuchMethodException {
        String methodName=point.getSignature().getName();
        Class<?> classTarget = point.getTarget().getClass();
        Class<?>[] par=((MethodSignature) point.getSignature()).getParameterTypes();
        Method objMethod=classTarget.getMethod(methodName, par);
        return objMethod.getAnnotation(ParamsLog2.class);
    }
    public ParamsLog2 getParamsLog2(JoinPoint joinPoint) throws ClassNotFoundException {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        ParamsLog2 paramsLog = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    paramsLog = method.getAnnotation(ParamsLog2.class);
                }
            }
        }
        return paramsLog;
    }
}
