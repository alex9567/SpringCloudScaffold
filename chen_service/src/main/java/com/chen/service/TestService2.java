package com.chen.service;

import com.chen.service.requestDTO.Test2RequestDTO;
import com.chen.service.requestDTO.TestHelloRequestDTO;
import com.chen.service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
}
