package com.chen.core.piplineHandler;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(90)
@Component
@Slf4j
public class RiskHandler implements Handler {

    /**
     * 处理接收到前端请求的逻辑
     */
    @Override
    public void oneTask(HandlerContext ctx, Request request) {
        request.setDeal(request.getDeal()+",doRiskOne");
        log.info("风险拦截,接受逻辑");
        log.info(new Gson().toJson(request));
        //ctx.fireTaskOne(request);
    }

    /**
     * 查询到task之后，进行task过滤的逻辑
     */
    @Override
    public void twoTask(HandlerContext ctx, Task task) {
        task.setDescription("new Task");
        task.setNum(0);
        log.info("风险拦截,过滤逻辑");
        log.info(new Gson().toJson(task));
        ctx.fireTaskTwo(task);
    }

    /**
     * task过滤完成之后，处理执行task的逻辑
     */
    @Override
    public void threeTask(HandlerContext ctx, Task task) {
        log.info("风险拦截,执行逻辑");
        ctx.fireTaskThree(task);
    }

    @Override
    public void exceptionCaught(HandlerContext ctx, Exception e) {
        log.info("Error",e);
    }

}
