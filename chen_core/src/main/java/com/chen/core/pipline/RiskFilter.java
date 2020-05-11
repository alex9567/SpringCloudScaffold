package com.chen.core.pipline;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// 风控拦截
@Component
@Order(90)
public class RiskFilter implements Filter {

    @Override
    public boolean filter(Task task) {
        System.out.println("风控拦截");
        return true;
    }
}
