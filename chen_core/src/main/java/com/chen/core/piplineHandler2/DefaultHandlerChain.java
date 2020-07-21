package com.chen.core.piplineHandler2;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultHandlerChain implements HandlerChain, InitializingBean {
    @Resource
    private List<Handler> handlers = new ArrayList<>();
    private HandlerRequest handlerRequest;
    private HandlerTask handlerTask;
    private HandlerContext handlerContext;

    @Override
    public void doChain(HandlerRequest handlerRequest) {
        this.handlerRequest = handlerRequest;
        this.handlerTask = new HandlerTask();
        handlerTask.setDescription("test");
        handlerTask.setNum(0);
        handlerContext.doHandler(this.handlerRequest,this.handlerTask);
    }

    HandlerContext getNewHandlerContext(Handler handler) {
        HandlerContext handlerContext = new HandlerContext();
        handlerContext.setHandler(handler);
        return handlerContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        handlerContext = new HandlerContext();
        handlerContext.setHandler(new Handler() {
        });
        for (Handler handler : handlers) {
            handlerContext.initSetNext(getNewHandlerContext(handler));
        }
    }
}
