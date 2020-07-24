package com.chen.core.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

// 虚拟币发放
@Component
@Slf4j
public class VirtualCurrencySender implements PrizeSender {

    @Override
    public boolean support(SendPrizeRequest request) {
        return request.getPrizeType() == 2;
    }

    @Override
    public void sendPrize(SendPrizeRequest request) {
        log.info("发放虚拟币");
    }
}
