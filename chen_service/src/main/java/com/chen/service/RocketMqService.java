package com.chen.service;


import com.chen.service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "SERVICE-A")
@RequestMapping("/mq")
public interface RocketMqService {
    @RequestMapping("/sendOne")
    public Result<String> openAccountMsg(String msgInfo);
}
