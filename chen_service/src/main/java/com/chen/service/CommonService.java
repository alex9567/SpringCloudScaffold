package com.chen.service;

import com.chen.service.requestDTO.Test2RequestDTO;
import com.chen.service.requestDTO.TestHelloRequestDTO;
import com.chen.service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.BindingResult;
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
    @RequestMapping("/test6")
    public Result<String> test6(String a);
    @RequestMapping("/test7")
    public Result<String> test7(String a);
    @RequestMapping("/test8")
    public Result<String> test8(int a);
    @RequestMapping("/test9")
    public Result<String> test9(String a);
    @RequestMapping("/test10")
    public Result<String> test10(int a);
    @RequestMapping("/test11")
    public Result<String> test11(Test2RequestDTO test2RequestDTO);
    @RequestMapping("/test12")
    public Result<String> test12(Test2RequestDTO test2RequestDTO);
}
