package com.chen.core.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ContextTest {
    public void execute(String params){
        Context context = init(params);
        first(context);
        second(context);
    }
    private Context init(String params){
        Context context = new Context();
        context.setParams(params);
        return context;
    }
    private void first(Context context){
        String params = context.getParams();
        context.setFirstResult(params+"first");
        log.info(context.getFirstResult());
    }
    private void second(Context context){
        String params = context.getParams();
        context.setSecondResult(params+"second");
        log.info(context.getSecondResult());
    }
    private void result(Context context){
        String params = context.getParams();
        String first = context.getFirstResult();
        String second = context.getSecondResult();
        context.setResult(first+second);
        log.info(context.getSecondResult());
    }
}

