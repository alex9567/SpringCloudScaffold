package com.chen.core.service.impl;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.chen.common.exception.BaseException;
import com.chen.common.logAop.ParamsLog;
import com.chen.common.logAop.TraceLog;
import com.chen.common.nacos.ChenConfigInfo;
import com.chen.core.pipline.ApplicationService;
import com.chen.core.piplineHandler.ApplicationService2;
import com.chen.core.piplineHandler.Request;
import com.chen.core.process.TestProcessFactory;
import com.chen.core.process.service.TsetProcessService;
import com.chen.service.CommonService;
import com.chen.service.requestDTO.Test2RequestDTO;
import com.chen.service.requestDTO.TestHelloRequestDTO;
import com.chen.service.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
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
    @Resource
    ApplicationService2 applicationService2;
    @Override
    public Result<String> test1() {
        return Result.success("test1");
    }

    /**
     * 简单测试方法返回值
     * @return
     */
    @Override
    public Result<List<String>> test2() {
        List<String> list = new ArrayList<>();
        list.add("test2");
        return Result.success(list);
    }

    /**
     * 测试BaseException异常捕获
     * @param requestDTO
     * @return
     */
    @Override
    public Result<String> test3(@RequestBody TestHelloRequestDTO requestDTO) {
        if(1==1){
            throw new BaseException("test3","11","11");
        }
        return Result.success("test3");
    }

    /**
     * 测试exception异常捕获
     * @param a
     * @return
     */
    @Override
    public Result<String> test4(String a) {
        int b = 1/0;
        return Result.success("test4");
    }

    /**
     * 测试3个日志格式的信息，以及nacos普通注入
     * @param a
     * @return
     */
    @Override
    public Result<String> test5(String a) {
        log.info("test5");
        log.warn("test5");
        log.error("test5");
        return Result.success(hello);
    }

    /**
     * 测试自定义nacos的json配置使用
     * @param a
     * @return
     */
    @Override
    public Result<String> test6(String a) {
        return Result.success(chenConfigInfo.getData().getAge());
    }

    /**
     * 测试工厂类
     * @param a
     * @return
     */
    @Override
    @ParamsLog
    @TraceLog
    public Result<String> test7(String a) {
        TsetProcessService service = testProcessFactory.getProcessService(a);
        String result = service.printName();
        return Result.success(result);
    }

    /**
     * 测试工厂类的优化
     * @param a
     * @return
     */
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

    /**
     * 测试责任链
     * @param a
     * @return
     */
    @Override
    public Result<String> test9(String a) {
        applicationService.mockedClient();
        return Result.success("scuuess");
    }

    @Override
    public Result<String> test10(int a) {
        return null;
    }

    /**
     * 测试@Valid的另一种异常捕获
     * @param test2RequestDTO
     * @return
     */
    @Override
    @ParamsLog
    @TraceLog
    public Result<String> test11(@Valid @RequestBody Test2RequestDTO test2RequestDTO) {
        return Result.success("scuuess");
    }

    /**
     * 测试不加@RequestBody的影响
     * @param test2RequestDTO
     * @return
     */
    @Override
    @ParamsLog
    @TraceLog
    public Result<String> test12(Test2RequestDTO test2RequestDTO) {
        return Result.success("scuuess");
    }

    /**
     * 高级版本的责任链
     * @return
     */
    @Override
    public Result<String> test13(String name) {
        Request request = new Request();
        request.setName(name);
        request.setDeal("0");
        applicationService2.mockedClient(request);
        return Result.success("success");
    }
}
