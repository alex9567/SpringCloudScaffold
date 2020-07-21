package com.chen.core.piplineHandler2;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(100)
public class OneHandler implements Handler {

    @Override
    public void doHandler(HandlerContext handlerContext,HandlerRequest handlerRequest, HandlerTask handlerTask) {
        log.info("One");
        handlerRequest.setDeal("one");
        handlerTask.setNum(handlerTask.getNum()+1);
        log.info("handlerRequest:{},handlerTask:{}",new Gson().toJson(handlerRequest),new Gson().toJson(handlerTask));
        handlerContext.doHandler(handlerRequest,handlerTask);
    }

}
