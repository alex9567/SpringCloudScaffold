package com.chen.core.service.impl;


import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.chen.config.logAop.ParamsLog;
import com.chen.config.logAop.TraceLog;
import com.chen.config.redis.RedisUtil;
import com.chen.core.manager.TestMananger;
import com.chen.service.TestService;
import com.chen.service.requestDTO.TestHelloRequestDTO;
import com.chen.service.requestDTO.TestRedisRequestDTO;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;


@Slf4j
@RestController
@NacosPropertySource(dataId = "CHEN_COMMON_CONFIG2", groupId = "DEFAULT_GROUP", autoRefreshed = true)
public class TestServiceImpl implements TestService {
    //@NacosValue(value = "${hi:111}",autoRefreshed = true)
    @NacosValue(value = "${hi:test1}", autoRefreshed = true)
    private String hi;
    @Resource
    TestMananger testMananger;
    @Resource
    RedisUtil redisUtil;

    /**
     * 简单测试@RequestBody的注入效果
     *
     * @param requestDTO
     * @return
     */
    @Override
    public String hello(@RequestBody TestHelloRequestDTO requestDTO) {
        log.info(new Gson().toJson(requestDTO));
        return "hello";
    }

    /**
     * 测试aop日志和trace
     *
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
     *
     * @return
     */
    @Override
    public String one() {
        return testMananger.getOne();
    }

    /**
     * 测试mybatis
     *
     * @return
     */
    @Override
    public String two() {
        return testMananger.getTwo();
    }

    /**
     * 测试分页
     *
     * @return
     */
    @Override
    public String oneByPage() {
        return testMananger.getOneByPage();
    }

    /**
     * 测试分页
     *
     * @return
     */
    @Override
    public String oneByPage2() {
        return testMananger.getTwoByPage();
    }

    /**
     * 测试分布式事务，同事插入2个库，以及traceid是否以及在日志中体现
     *
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
     *
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
     *
     * @return
     */
    @Override
    public String redisInsert() {
        redisUtil.set("key", "chen");
        return "redis";
    }

    /**
     * redis geo插入
     *
     * @return
     */
    @Override
    public String geoAdd(@RequestBody TestRedisRequestDTO testRedisRequestDTO) {
        GeoCoordinate geoCoordinate = new GeoCoordinate(testRedisRequestDTO.getLongitude(), testRedisRequestDTO.getLatitude());
        redisUtil.geoadd(testRedisRequestDTO.getKey(), geoCoordinate, testRedisRequestDTO.getMember());
        return "success";
    }

    /**
     * redis geo删除
     *
     * @return
     */
    @Override
    public String geoRemove(@RequestBody TestRedisRequestDTO testRedisRequestDTO) {
        redisUtil.geoRemove(testRedisRequestDTO.getKey(), testRedisRequestDTO.getMember());
        return "success";
    }

    /**
     * redis geo获得列表
     *
     * @return
     */
    @Override
    public String geoRadius(@RequestBody TestRedisRequestDTO testRedisRequestDTO) {
        GeoCoordinate geoCoordinate = new GeoCoordinate(testRedisRequestDTO.getLongitude(), testRedisRequestDTO.getLatitude());
        List<GeoRadiusResponse> radiusResponses = redisUtil.geoRadius(testRedisRequestDTO.getKey(), geoCoordinate, testRedisRequestDTO.getRadius(), GeoUnit.M);
        for (GeoRadiusResponse geoRadiusResponse : radiusResponses) {
            try {
                String strContent = new String(geoRadiusResponse.getMember(), "utf-8");
                log.info(new Gson().toJson(strContent));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        log.info(new Gson().toJson(radiusResponses));
        return "success";
    }

    /**
     * redis geo距离判断
     *
     * @return
     */
    @Override
    public String geoDist(@RequestBody TestRedisRequestDTO testRedisRequestDTO) {
        Double result = redisUtil.geoDist(testRedisRequestDTO.getKey(), testRedisRequestDTO.getMember(), testRedisRequestDTO.getMember2(), GeoUnit.M);
        log.info(new Gson().toJson(result));
        return "success";
    }

    /**
     * 批量获得geo对应的
     *
     * @param testRedisRequestDTO
     * @return
     */
    @Override
    public String geoPos(@RequestBody TestRedisRequestDTO testRedisRequestDTO) {
        List<GeoCoordinate> result = redisUtil.geoPos(testRedisRequestDTO.getKey(), testRedisRequestDTO.getMember(), testRedisRequestDTO.getMember2());
        log.info(new Gson().toJson(result));
        return "success";
    }


    /**
     * 测试获得nacos的配置
     *
     * @return
     */
    @Override
    public String getNacos() {
        log.info("hi:{}", hi);
        return "success";
    }
}
