package com.chen.core.service.impl;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.chen.service.TestService2;
import com.chen.service.requestDTO.TestHelloRequestDTO;
import com.chen.service.result.Result;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@NacosPropertySource(dataId = "CHEN_COMMON_CONFIG", groupId = "DEFAULT_GROUP", autoRefreshed = true)
public class TestService2Impl implements TestService2 {
    //@NacosValue(value = "${hi:111}",autoRefreshed = true)
    @NacosValue(value = "${hi:test2}", autoRefreshed = true)
    private String hi;
    @Override
    public Result<String> test1() {
        return Result.success("success");
    }

    @Override
    public Result<String> test2() {
        return Result.success("success");
    }

    @Override
    public Result<String> test3(@RequestBody TestHelloRequestDTO requestDTO) {
        log.info(new Gson().toJson(requestDTO));
        return Result.success("success");
    }

    @Override
    public Result<String> test4(@RequestBody TestHelloRequestDTO requestDTO) {
        log.info(new Gson().toJson(requestDTO));
        return Result.success("success");
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
