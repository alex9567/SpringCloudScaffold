package com.chen.core.strategy;

public interface PrizeSender {
    /**
     * 用于判断当前实例是否支持当前奖励的发放
     */
    boolean support(SendPrizeRequest request);

    /**
     * 发放奖励
     */
    void sendPrize(SendPrizeRequest request);
}
