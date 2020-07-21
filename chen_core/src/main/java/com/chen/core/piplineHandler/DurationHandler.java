package com.chen.core.piplineHandler;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(110)
@Component
@Slf4j
public class DurationHandler implements Handler {
    /**
     * 处理接收到前端请求的逻辑
     */
    @Override
    public void oneTask(HandlerContext ctx, Request request) {
        log.info("时效性检验,接受逻辑");
        request.setDeal(request.getDeal()+",doDuratioOne");
        log.info(new Gson().toJson(request));
        ctx.fireTaskOne(request);
    }

    /**
     * 查询到task之后，进行task过滤的逻辑
     */
    @Override
    public void twoTask(HandlerContext ctx, Task task) {
        log.info("时效性检验,过滤逻辑");
        task.setNum(task.getNum()+1);
        log.info(new Gson().toJson(task));
        ctx.fireTaskTwo(task);
    }

    /**
     * task过滤完成之后，处理执行task的逻辑
     */
    @Override
    public void threeTask(HandlerContext ctx, Task task) {
        log.info("时效性检验,执行逻辑");
        ctx.fireTaskThree(task);
    }
}
