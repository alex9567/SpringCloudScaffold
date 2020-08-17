package com.chen.common.taskPool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * 线程池相关调用类
 */
@Component
@Slf4j
public class TaskExceute {
    @Async("taskExecutor")
    public void tesTask(int i) {
        log.info(Thread.currentThread().getName() + "-----" + i);
    }

    @Async("taskExecutor")
    public void stringTask(String str) {

        log.info(Thread.currentThread().getName() + str);
    }

    @Async("taskExecutor")
    public Future<String> returnResultTask(String str) {

        log.info(Thread.currentThread().getName() + str);
        return new AsyncResult<>(str);
    }
}
