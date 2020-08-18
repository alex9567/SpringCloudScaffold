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
    @RequestMapping(method = RequestMethod.POST, value = "/test1")
    public Result<String> test1();
    @RequestMapping(method = RequestMethod.POST, value = "/test2")
    public Result<String> test2();
    @RequestMapping(method = RequestMethod.POST, value = "/test3")
    public Result<String> test3(@RequestBody TestHelloRequestDTO requestDTO);
}
