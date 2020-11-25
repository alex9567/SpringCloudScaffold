package com.chen.service;


import com.chen.service.requestDTO.TestOpenRequestDTO;
import com.chen.service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "SERVICE-A")
@RequestMapping("/open")
public interface OpenService {
    @RequestMapping("/test")
    public Result<String> test(TestOpenRequestDTO requestDTO);
}
