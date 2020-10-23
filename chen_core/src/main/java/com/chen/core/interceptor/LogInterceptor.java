package com.chen.core.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
@Slf4j
public class LogInterceptor extends HandlerInterceptorAdapter {
    private static final String HEAD_ID = "HEAD_ID";
    private static final String REQUEST_HEAD_ID = "head_id";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String headId = request.getHeader(REQUEST_HEAD_ID);
        log.info("preHandle before headId:{}",headId);
        if (headId == null) {
            headId = UUID.randomUUID().toString();
            request.setAttribute(REQUEST_HEAD_ID,headId);
        }
        if (StringUtils.isEmpty(MDC.get(HEAD_ID))) {
            MDC.put(HEAD_ID, headId);
        }
        log.info("preHandle after headId:{}",headId);
        return true;
    }
}
