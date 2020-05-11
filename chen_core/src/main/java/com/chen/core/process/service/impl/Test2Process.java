package com.chen.core.process.service.impl;


import com.chen.core.process.service.TsetProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Test2Process implements TsetProcessService {
    @Override
    public String printName() {
        log.info("test2 coming");
        return "test2";
    }

    @Override
    public String getType() {
        return "test2";
    }

    @Override
    public int getNum() {
        return 2;
    }
}
