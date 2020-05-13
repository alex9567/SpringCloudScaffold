package com.chen.core.mqListener;

import com.chen.common.logAop.ParamsLog;
import com.chen.common.logAop.TraceLog;
import com.chen.common.mq.ParamConfigService;
import com.chen.core.service.MqConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 消息消费监听
 */
@Component
@Slf4j
public class RocketMsgListener implements MessageListenerConcurrently {

    @Resource
    private ParamConfigService paramConfigService;
    @Resource
    MqConsumerService mqConsumerService;

    @Override
    @TraceLog
    @ParamsLog
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
        if (CollectionUtils.isEmpty(list)) {
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = list.get(0);
        log.info("接受到的消息为：" + new String(messageExt.getBody()));
        int reConsume = messageExt.getReconsumeTimes();
        // 消息已经重试了3次，如果不需要再次消费，则返回成功
        if (reConsume == 3) {
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        if (messageExt.getTopic().equals(paramConfigService.rocketTopic)) {
            String tags = messageExt.getTags();
            switch (tags) {
                case "rocketTag":
                    log.info("开户 tag == >>" + tags);
                    try {
                        mqConsumerService.deal1(messageExt.getMsgId());
                    } catch (Exception e) {
                        log.error("error", e);
                    }

                    log.info("end == >>" + tags);
                    break;
                default:
                    log.info("未匹配到Tag == >>" + tags);
                    break;
            }
        }
        // 消息消费成功
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}
