package com.chen.common.taskPool;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@Slf4j
public class MdcThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
    private final static String LOG_ID = "logId";

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        Map<String, String> context = MDC.getCopyOfContextMap();
        log.info("----MDC content:{}", JSONObject.toJSONString(context));
        return super.submit(() -> {
            // 将父线程的MDC内容传给子线程
            T result = null;
            if (!CollectionUtils.isEmpty(context)) {
                MDC.setContextMap(context);
            } else {
                MDC.put(LOG_ID, UUID.randomUUID().toString().replace("-", "")); //为空设置新值
            }
            try {
                result = task.call();
            } finally {
                try {
                    MDC.clear();
                } catch (Exception e2) {
                    log.warn("mdc clear exception.", e2);
                }
            }
            return result;
        });
    }
}