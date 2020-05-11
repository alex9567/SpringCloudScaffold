package com.chen.core.process.service.impl;


import com.chen.core.process.service.TsetProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Test1Process implements TsetProcessService {
    @Override
    public String printName() {
        log.info("test1 coming");
        return "test1";
    }

    @Override
    public String getType() {
        return "test1";
    }

    @Override
    public int getNum() {
        return 1;
    }

    @Override
    public String first() {
        log.info("test1 first");
        return "test1";
    }

    @Override
    public String second() {
        log.info("test1 second");
        return "test1";
    }
}
