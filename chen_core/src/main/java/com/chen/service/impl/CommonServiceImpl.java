package com.chen.service.impl;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.chen.common.exception.BaseException;
import com.chen.common.web.Result;
import com.chen.service.CommonService;
import com.chen.service.RequestDTO.TestHelloRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@NacosPropertySource(dataId = "CHEN_COMMON_CONFIG2" ,groupId = "DEFAULT_GROUP2", autoRefreshed = true)
public class CommonServiceImpl implements CommonService {
    @NacosValue(value = "${hi}",autoRefreshed = true)
    private String hello;
    @Override
    public Result<String> test1() {
        return Result.success("test1");
    }

    @Override
    public Result<List<String>> test2() {
        List<String> list = new ArrayList<>();
        list.add("test2");
        return Result.success(list);
    }

    @Override
    public Result<String> test3(@RequestBody TestHelloRequestDTO requestDTO) {
        if(1==1){
            throw new BaseException("test3","11","11");
        }
        return Result.success("test3");
    }

    @Override
    public Result<String> test4(String a) {
        int b = 1/0;
        return Result.success("test4");
    }

    @Override
    public Result<String> test5(String a) {
        return Result.success(hello);
    }
}
