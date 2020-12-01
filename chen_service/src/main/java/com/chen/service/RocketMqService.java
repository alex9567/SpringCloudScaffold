package com.chen.service;


import com.chen.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "SERVICE-A")
@RequestMapping("/mq")
public interface RocketMqService {
    @RequestMapping("/sendOne")
    public Result<String> openAccountMsg(String key,String msgInfo);
    @RequestMapping("/sendOther")
    public Result<String> sendOther(String key,String msgInfo);
}
