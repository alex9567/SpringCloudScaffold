package com.chen.service.impl;

import com.alibaba.fastjson.JSON;
import com.chen.common.redis.RedisUtil;
import com.chen.core.manager.TestMananger;
import com.chen.service.RequestDTO.TestHelloRequestDTO;
import com.chen.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Slf4j
@RestController
public class TestServiceImpl implements TestService {
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
    public String insertOne() {
        log.info("service#insertOne");
        return testMananger.insertOne();
    }

    @Override
    public String insertTwo() {
        return testMananger.insertTwo();
    }

    @Override
    public String redisInsert() {
        redisUtil.set("key","chen");
        return "redis";
    }
}
