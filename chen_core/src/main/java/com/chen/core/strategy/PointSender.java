package com.chen.core.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

// 积分发放
@Component
@Slf4j
public class PointSender implements PrizeSender {

    @Override
    public boolean support(SendPrizeRequest request) {
        return request.getPrizeType() == 1;
    }

    @Override
    public void sendPrize(SendPrizeRequest request) {
        log.info("发放积分");
    }
}
