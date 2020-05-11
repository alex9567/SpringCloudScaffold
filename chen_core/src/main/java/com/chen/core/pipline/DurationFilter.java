package com.chen.core.pipline;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// 时效性检验
@Component
@Order(80)
public class DurationFilter implements Filter {

    @Override
    public boolean filter(Task task) {
        System.out.println("时效性检验");
        return true;
    }
}

