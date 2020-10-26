package com.chen.common.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.UnsupportedEncodingException;

@Slf4j
public class AffairsMsgListener implements TransactionListener {
    /**
     * COMMIT：提交  ROLLBACK：回滚  UNKNOW：回调
     *
     * @param msg
     * @param o
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object o) {
        log.info("回调方法，message:{}", msg);
        String msgBody;
        //执行本地业务的时候，再插入一条数据到事务表中，供checkLocalTransaction进行check使用，避免doBusinessCommit业务成功，但是未返回Commit
        try {
            msgBody = new String(msg.getBody(), "utf-8");
            String key = msg.getKeys();
            if (key.contains("fail")) {
                doBusinessfail(key, msgBody);
                return LocalTransactionState.ROLLBACK_MESSAGE;
            } else if (key.contains("unknown")) {
                doBusinessNuknow(key, msgBody);
                return LocalTransactionState.UNKNOW;
            } else {
                doBusinessCommit(key, msgBody);
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return LocalTransactionState.ROLLBACK_MESSAGE;
        } catch (Exception e) {
            e.printStackTrace();
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        log.info("检查方法，MessageExt:{}", msg);
        Boolean result = checkBusinessStatus(msg.getKeys());
        if (result) {
            return LocalTransactionState.COMMIT_MESSAGE;
        } else {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
    }

    public static void doBusinessCommit(String messageKey, String msgbody) {
        log.info("insert 事务消息到本地消息表中，消息执行成功，messageKey为：" + messageKey);
    }

    public static void doBusinessfail(String messageKey, String msgbody) {
        log.info("insert 事务消息到本地消息表中，消息执行失败！messageKey为：" + messageKey);
    }

    public static void doBusinessNuknow(String messageKey, String msgbody) {
        log.info("insert 事务消息到本地消息表中，消息执行无响应！messageKey为：" + messageKey);
    }

    public static Boolean checkBusinessStatus(String messageKey) {
        if (messageKey.contains("unknownSuccess")) {
            log.info("查询数据库 messageKey为" + messageKey + "的消息已经消费成功了，可以提交消息");
            return true;
        }
        if(messageKey.contains("unknownFail")) {
            log.info("查询数据库 messageKey为" + messageKey + "的消息不存在或者未消费成功了，可以回滚消息");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String a = "tru2e1";
        System.out.println(a.contains("true"));
    }
}
