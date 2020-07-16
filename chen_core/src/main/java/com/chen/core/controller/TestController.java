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
    @ParamsCheck
    @TraceLog
    @RequestMapping("/test11")
    public Result<String> test11(@Valid Test2RequestDTO test2RequestDTO, BindingResult result) {
        return Result.success("scuuess");
    }

    @ParamsCheck
    @TraceLog
    @RequestMapping("/test12")
    public Result<String> test12(@Valid Test2RequestDTO test2RequestDTO, BindingResult result) {
        return Result.success("scuuess");
    }

    @TraceLog
    @ParamsCheck
    @RequestMapping("/test13")
    public Result<String> test13(@Valid Test1RequestDTO test1RequestDTO, BindingResult result) {
        return Result.success("scuuess");
    }
}
