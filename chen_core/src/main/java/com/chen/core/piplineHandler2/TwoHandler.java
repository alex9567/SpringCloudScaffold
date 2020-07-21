package com.chen.core.piplineHandler2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(110)
public class TwoHandler implements Handler {
    @Override
    public void doHandler(HandlerContext handlerContext,HandlerRequest handlerRequest, HandlerTask handlerTask) {
        log.info("Two");
    }
}
