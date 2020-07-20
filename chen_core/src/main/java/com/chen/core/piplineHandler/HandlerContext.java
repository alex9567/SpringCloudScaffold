package com.chen.core.piplineHandler;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class HandlerContext {
    /**
     * 上一个
     */
    HandlerContext prev;
    /**
     * 下一个
     */
    HandlerContext next;
    Handler handler;

    private Task task;

    public void fireTaskOne(Request request) {
        invokeTaskOne(next(), request);
    }

    /**
     * 处理接收到任务的事件
     */
    static void invokeTaskOne(HandlerContext ctx, Request request) {
        if (ctx != null) {
            try {
                ctx.handler().oneTask(ctx, request);
            } catch (Throwable e) {
                ctx.handler().exceptionCaught(ctx, e);
            }
        }
    }

    public void fireTaskTwo(Task task) {
        invokeTaskTwo(next(), task);
    }

    /**
     * 处理任务过滤事件
     */
    static void invokeTaskTwo(HandlerContext ctx, Task task) {
        if (null != ctx) {
            try {
                ctx.handler().twoTask(ctx, task);
            } catch (Throwable e) {
                ctx.handler().exceptionCaught(ctx, e);
            }
        }
    }

    public void fireTaskThree(Task task) {
        invokeTaskThree(next(), task);
    }

    /**
     * 处理执行任务事件
     */
    static void invokeTaskThree(HandlerContext ctx, Task task) {
        if (null != ctx) {
            try {
                ctx.handler().threeTask(ctx, task);
            } catch (Exception e) {
                ctx.handler().exceptionCaught(ctx, e);
            }
        }
    }

    public void fireAfterCompletion(HandlerContext ctx) {
        invokeAfterCompletion(next());
    }

    static void invokeAfterCompletion(HandlerContext ctx) {
        if (null != ctx) {
            ctx.handler().afterCompletion(ctx);
        }
    }

    private HandlerContext next() {
        return next;
    }

    private Handler handler() {
        return handler;
    }
}
