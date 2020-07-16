package com.chen.core.service.impl;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.chen.common.exception.BaseException;
import com.chen.common.logAop.ParamsLog;
import com.chen.common.logAop.TraceLog;
import com.chen.common.nacos.ChenConfigInfo;
import com.chen.core.pipline.ApplicationService;
import com.chen.core.process.TestProcessFactory;
import com.chen.core.process.service.TsetProcessService;
import com.chen.service.CommonService;
import com.chen.service.requestDTO.Test2RequestDTO;
import com.chen.service.requestDTO.TestHelloRequestDTO;
import com.chen.service.result.Result;
import com.chen.service.result.ResultEnum;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@NacosPropertySource(dataId = "CHEN_COMMON_CONFIG3" ,groupId = "DEFAULT_GROUP", autoRefreshed = true)
public class CommonServiceImpl implements CommonService {
    //@NacosValue(value = "${hi:111}",autoRefreshed = true)
    @NacosValue(value = "${hi}",autoRefreshed = true)
    private String hello;
    @Resource
    ChenConfigInfo chenConfigInfo;
    @Resource
    TestProcessFactory testProcessFactory;
    @Resource
    ApplicationService applicationService;
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
        log.info("test5");
        log.warn("test5");
        log.error("test5");
        return Result.success(hello);
    }

    @Override
    public Result<String> test6(String a) {
        return Result.success(chenConfigInfo.getData().getAge());
    }

    @Override
    @ParamsLog
    @TraceLog
    public Result<String> test7(String a) {
        TsetProcessService service = testProcessFactory.getProcessService(a);
        String result = service.printName();
        return Result.success(result);
    }
    @Override
    @ParamsLog
    @TraceLog
    public Result<String> test8(int a) {
        List<TsetProcessService> serviceList = testProcessFactory.getProcessServiceByInt(a);
        for(TsetProcessService service:serviceList){
            service.printName();
        }
        return Result.success("scuuess");
    }

    @Override
    public Result<String> test9(String a) {
        applicationService.mockedClient();
        return Result.success("scuuess");
    }

    @Override
    public Result<String> test10(int a) {
        return null;
    }

    @Override
    @ParamsLog
    @TraceLog
    public Result<String> test11(@RequestBody Test2RequestDTO test2RequestDTO) {
        return Result.success("scuuess");
    }

    @Override
    @ParamsLog
    @TraceLog
    public Result<String> test12(Test2RequestDTO test2RequestDTO) {
        return Result.success("scuuess");
    }
}
