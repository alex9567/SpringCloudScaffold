package com.chen.service;

import com.chen.common.web.Result;
import com.chen.service.RequestDTO.TestHelloRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "SERVICE-A")
public interface CommonService {
    @RequestMapping("/test1")
    public Result<String> test1();
    @RequestMapping("/test2")
    public Result<List<String>> test2();
    @RequestMapping("/test3")
    public Result<String> test3(TestHelloRequestDTO requestDTO);
    @RequestMapping("/test4")
    public Result<String> test4(String a);
    @RequestMapping("/test5")
    public Result<String> test5(String a);

}
