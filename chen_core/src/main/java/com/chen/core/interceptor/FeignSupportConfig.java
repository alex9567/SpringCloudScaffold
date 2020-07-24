package com.chen.core.interceptor;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign配置注册（全局）
 *
 * @author simon
 * @create 2018-08-20 11:44
 **/
@Configuration
public class FeignSupportConfig {
    @Autowired
    FeignTrackInterceptor feignTrackInterceptor;

    /**
     * feign请求拦截器
     *
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return feignTrackInterceptor;
    }
}
