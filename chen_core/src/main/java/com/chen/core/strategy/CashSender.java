package com.chen.core.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

// 现金发放
@Component
@Slf4j
public class CashSender implements PrizeSender {

    @Override
    public boolean support(SendPrizeRequest request) {
        return request.getPrizeType() == 3;
    }

    @Override
    public void sendPrize(SendPrizeRequest request) {
        log.info("发放现金");
    }
}
