package com.chen.core.interceptor;

import com.google.gson.Gson;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class FeignTrackInterceptor implements RequestInterceptor {
    private String headTraceId = "head_trace_id";
    private String traceId = "HEAD_TRACE_ID";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("request:{}", new Gson().toJson(request.getHeader("head_id")));
        log.info("feign:{}", new Gson().toJson(requestTemplate.header("head_id")));
        //head_id
        if (request.getHeader("head_id") != null) {
            requestTemplate.header("head_id", request.getHeader("head_id"));
        }

    }
}
