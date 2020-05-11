package com.chen.core.pipline;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// 次数限制校验
@Component
@Order(100)
public class TimesFilter implements Filter {

    @Override
    public boolean filter(Task task) {
        System.out.println("次数限制检验");
        return true;
    }
}
