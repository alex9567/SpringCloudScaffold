package com.chen.core.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class LogInterceptor extends HandlerInterceptorAdapter {
    private static final String HEAD_ID = "HEAD_ID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String headId = request.getHeader("head_id");
        if (headId == null) {
            headId = UUID.randomUUID().toString();
        }
        if (StringUtils.isEmpty(MDC.get(HEAD_ID))) {
            MDC.put(HEAD_ID, headId);
        }
        return true;
    }
}
