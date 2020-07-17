package com.chen.core.controller;


import com.chen.common.logAop.ParamsCheck;
import com.chen.common.logAop.ParamsLog;
import com.chen.common.logAop.TraceLog;
import com.chen.service.requestDTO.Test1RequestDTO;
import com.chen.service.requestDTO.Test2RequestDTO;
import com.chen.service.result.Result;
import com.chen.service.result.ResultEnum;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/controller")
public class TestController {
    /**
     * aop处理@Valid
     * @param test1RequestDTO
     * @param result
     * @return
     */
    @ParamsCheck
    @TraceLog
    @RequestMapping("/test11")
    public Result<String> test11(@Valid Test1RequestDTO test1RequestDTO, BindingResult result) {
        return Result.success("scuuess");
    }

    /**
     * aop处理@Valid
     * @param test1RequestDTO
     * @param result
     * @return
     */
    @ParamsCheck
    @TraceLog
    @RequestMapping("/test12")
    public Result<String> test12(@Valid Test1RequestDTO test1RequestDTO, BindingResult result) {
        return Result.success("scuuess");
    }

    /**
     * 用过@Valid写法
     * @param test1RequestDTO
     * @param result
     * @return
     */
    @RequestMapping("/test13")
    public Result<String> test13(@Valid Test1RequestDTO test1RequestDTO, BindingResult result) {
        if (result.hasErrors()) {
            log.info("paramsError,RequestArgs:{},errorMsg", new Gson().toJson(test1RequestDTO), new Gson().toJson(result.getAllErrors()));
        }
        return Result.success("scuuess");
    }

    /**
     * 用于测试@Valid如果加了BindingResult就不会有异常
     * @param test1RequestDTO
     * @param result
     * @return
     */
    @RequestMapping("/test14")
    public Result<String> test14(@Valid Test1RequestDTO test1RequestDTO, BindingResult result) {
        return Result.success("scuuess");
    }

    /**
     * 异常捕获捕获@Valid
     * @param test1RequestDTO
     * @return
     */
    @RequestMapping("/test15")
    @TraceLog
    @ParamsLog
    public Result<String> test15(@Valid Test1RequestDTO test1RequestDTO) {
        return Result.success("scuuess");
    }
}
