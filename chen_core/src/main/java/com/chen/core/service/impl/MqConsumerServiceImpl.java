package com.chen.core.service.impl;

import com.chen.core.service.MqConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MqConsumerServiceImpl implements MqConsumerService {

    @Override
    public String deal1(String message) {
        log.info("message:{}",message);
        return message;
    }
}
