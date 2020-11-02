package com.chen.core.service.impl;

import com.chen.common.logAop.ParamsLog;
import com.chen.common.logAop.ParamsLog2;
import com.chen.common.mq.AffairsMsgListener;
import com.chen.common.mq.ParamConfigService;
import com.chen.service.RocketMqService;
import com.chen.service.result.Result;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class RocketMqServiceImpl implements RocketMqService {
    @Resource(name = "producer1")
    private DefaultMQProducer defaultMQProducer;
    @Resource(name = "producer2")
    private TransactionMQProducer transactionMQProducer;
    @Resource
    private ParamConfigService paramConfigService;

    @Override
    public Result<String> openAccountMsg(String key,String msgInfo) {
        // 可以不使用Config中的Group
        //defaultMQProducer.setProducerGroup(paramConfigService.rocketGroup);
        SendResult sendResult = null;
        try {
            Message sendMsg = new Message(paramConfigService.rocketTopic,
                    paramConfigService.rocketTag,
                    key, msgInfo.getBytes());
            sendResult = defaultMQProducer.send(sendMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("sendResult:{}", new Gson().toJson(sendResult));
        return Result.success(sendResult.getMsgId());
    }

    @Override
    @ParamsLog2
    public Result<String> sendOther(String key,String msgInfo) {
        // 可以不使用Config中的Group
        //transactionMQProducer.setProducerGroup(paramConfigService.rocketGroup2);
        SendResult sendResult = null;
        try {
            Message sendMsg = new Message(paramConfigService.rocketTopic2,
                    paramConfigService.rocketTag2,
                    key, msgInfo.getBytes());
            sendResult = transactionMQProducer.sendMessageInTransaction(sendMsg,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("sendResult:{}", new Gson().toJson(sendResult));
        return Result.success(sendResult.getMsgId());
    }
}

