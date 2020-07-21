package com.chen.core.piplineHandler2;

public interface Handler {
    default void doHandler(HandlerContext handlerContext,HandlerRequest handlerRequest, HandlerTask handlerTask) {
        handlerContext.doHandler(handlerRequest,handlerTask);
    }
}
