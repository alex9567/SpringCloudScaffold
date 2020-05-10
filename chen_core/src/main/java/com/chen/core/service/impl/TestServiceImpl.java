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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Slf4j
@RestController
@NacosPropertySource(dataId = "CHEN_COMMON_CONFIG", groupId = "DEFAULT_GROUP", autoRefreshed = true)
public class TestServiceImpl implements TestService {
    @NacosValue(value = "${hi}", autoRefreshed = true)
    private String hello;
    @Resource
    TestMananger testMananger;
    @Resource
    RedisUtil redisUtil;

    @Override
    public String hello(@RequestBody TestHelloRequestDTO requestDTO) {
        log.info(JSON.toJSONString(requestDTO));
        return "hello";
    }

    @Override
    @TraceLog
    @ParamsLog
    public String hi() {
        return "hi";
    }

    @Override
    public String one() {
        return testMananger.getOne();
    }

    @Override
    public String two() {
        return testMananger.getTwo();
    }

    @Override
    public String oneByPage() {
        return testMananger.getOneByPage();
    }

    @Override
    public String oneByPage2() {
        return testMananger.getTwoByPage();
    }

    @Override
    @TraceLog
    @ParamsLog
    public String insertOne() {
        log.info("service#insertOne");
        return testMananger.insertOne();
    }

    @Override
    @TraceLog
    @ParamsLog
    public String insertTwo() {
        return testMananger.insertTwo();
    }

    @Override
    public String redisInsert() {
        redisUtil.set("key", "chen");
        return "redis";
    }

    @Override
    public String getNacos() {
        return hello;
    }
}
