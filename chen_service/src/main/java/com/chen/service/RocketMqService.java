package com.chen.service;

import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "SERVICE-A")
public interface RocketMqService {
    @RequestMapping("/sendOne")
    public SendResult openAccountMsg(String msgInfo);
}
