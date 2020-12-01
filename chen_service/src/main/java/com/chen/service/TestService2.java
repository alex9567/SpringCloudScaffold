package com.chen.service;

import com.chen.service.requestDTO.TestHelloRequestDTO;
import com.chen.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "SERVICE-A")
@RequestMapping("/test2")
public interface TestService2 {
    @RequestMapping("/test1")
    public Result<String> test1();

    @RequestMapping("/test2")
    public Result<String> test2();

    @RequestMapping("/test3")
    public Result<String> test3(TestHelloRequestDTO requestDTO);

    @RequestMapping("/test4")
    public Result<String> test4(TestHelloRequestDTO requestDTO);
    @RequestMapping("/getNacos")
    public String getNacos();
}
