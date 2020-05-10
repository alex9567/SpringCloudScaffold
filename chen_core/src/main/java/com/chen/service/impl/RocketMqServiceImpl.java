package com.chen.service.impl;

import com.alibaba.fastjson.JSON;
import com.chen.service.RocketMqService;
import com.chen.comon.mq.ParamConfigService;
import com.chen.service.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;

@RestController
@Slf4j
public class RocketMqServiceImpl implements RocketMqService {
    @Resource
    private DefaultMQProducer defaultMQProducer;
    @Resource
    private ParamConfigService paramConfigService ;
    @Override
    public Result<String> openAccountMsg(String msgInfo) {
        // 可以不使用Config中的Group
        defaultMQProducer.setProducerGroup(paramConfigService.rocketGroup);
        SendResult sendResult = null;
        try {
            Message sendMsg = new Message(paramConfigService.rocketTopic,
                    paramConfigService.rocketTag,
                    "open_account_key", msgInfo.getBytes());
            sendResult = defaultMQProducer.send(sendMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("sendResult:{}", JSON.toJSONString(sendResult));
        return Result.success(sendResult.getMsgId()) ;
    }
}

