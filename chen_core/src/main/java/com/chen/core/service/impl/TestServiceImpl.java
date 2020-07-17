package com.chen.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.chen.common.logAop.ParamsLog;
import com.chen.common.logAop.TraceLog;
import com.chen.common.redis.RedisUtil;
import com.chen.core.manager.TestMananger;
import com.chen.service.requestDTO.TestHelloRequestDTO;
import com.chen.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Slf4j
@RestController
@NacosPropertySource(dataId = "CHEN_COMMON_CONFIG", groupId = "DEFAULT_GROUP", autoRefreshed = true)
public class TestServiceImpl implements TestService {
    @NacosValue(value = "${hi}",autoRefreshed = true)
    private String hello;
    @Resource
    TestMananger testMananger;
    @Resource
    RedisUtil redisUtil;

    /**
     * 简单测试@RequestBody的注入效果
     * @param requestDTO
     * @return
     */
    @Override
    public String hello(@RequestBody TestHelloRequestDTO requestDTO) {
        log.info(JSON.toJSONString(requestDTO));
        return "hello";
    }

    /**
     * 测试aop日志和trace
     * @return
     */
    @Override
    @TraceLog
    @ParamsLog
    public String hi() {
        return "hi";
    }

    /**
     * 测试mybatis
     * @return
     */
    @Override
    public String one() {
        return testMananger.getOne();
    }

    /**
     * 测试mybatis
     * @return
     */
    @Override
    public String two() {
        return testMananger.getTwo();
    }

    /**
     * 测试分页
     * @return
     */
    @Override
    public String oneByPage() {
        return testMananger.getOneByPage();
    }

    /**
     * 测试分页
     * @return
     */
    @Override
    public String oneByPage2() {
        return testMananger.getTwoByPage();
    }

    /**
     * 测试分布式事务，同事插入2个库，以及traceid是否以及在日志中体现
     * @return
     */
    @Override
    @TraceLog
    @ParamsLog
    public String insertOne() {
        log.info("service#insertOne");
        return testMananger.insertOne();
    }

    /**
     * 测试没有开启注解导致没有事务
     * @return
     */
    @Override
    @TraceLog
    @ParamsLog
    public String insertTwo() {
        return testMananger.insertTwo();
    }

    /**
     * 测试redis 插入
     * @return
     */
    @Override
    public String redisInsert() {
        redisUtil.set("key", "chen");
        return "redis";
    }

    /**
     * 测试获得nacos的配置
     * @return
     */
    @Override
    public String getNacos() {
        return hello;
    }
}
