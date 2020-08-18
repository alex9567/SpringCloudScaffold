package com.chen.core.service.impl;

import com.chen.service.TestService2;
import com.chen.service.requestDTO.TestHelloRequestDTO;
import com.chen.service.result.Result;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
public class TestService2Impl implements TestService2 {
    @Override
    public Result<String> test1() {
        return Result.success("success");
    }

    @Override
    public Result<String> test2() {
        return Result.success("success");
    }

    @Override
    public Result<String> test3(TestHelloRequestDTO requestDTO) {
        log.info(new Gson().toJson(requestDTO));
        return Result.success("success");
    }

}
