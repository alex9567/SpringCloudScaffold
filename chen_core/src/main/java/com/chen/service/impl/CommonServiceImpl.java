package com.chen.service.impl;

import com.chen.common.exception.BaseException;
import com.chen.common.web.Result;
import com.chen.service.CommonService;
import com.chen.service.RequestDTO.TestHelloRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class CommonServiceImpl implements CommonService {

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
}
