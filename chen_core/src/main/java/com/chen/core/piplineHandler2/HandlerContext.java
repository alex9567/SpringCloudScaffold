package com.chen.core.piplineHandler2;


public class HandlerContext {
    Handler handler;
    HandlerContext next;

    public void doHandler(HandlerRequest handlerRequest, HandlerTask handlerTask) {
        handler.doHandler(next, handlerRequest, handlerTask);
    }

    public void initSetNext(HandlerContext handlerContext) {
        if (getNext() == null) {
            setNext(handlerContext);
        } else {
            next.initSetNext(handlerContext);
        }
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public HandlerContext getNext() {
        return next;
    }

    public void setNext(HandlerContext next) {
        this.next = next;
    }
}
