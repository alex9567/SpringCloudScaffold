package com.chen.service;

import com.chen.service.requestDTO.TestHelloRequestDTO;
import com.chen.service.requestDTO.TestRedisRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name = "SERVICE-A")
//@FeignClient注解value必须与服务客户端application.yml配置中服务命名对应
@RequestMapping("/test")
public interface TestService {
    @RequestMapping("/hello")
    public String hello(TestHelloRequestDTO requestDTO);
    @RequestMapping("/hi")
    public String hi();
    @RequestMapping("/one")
    public String one();
    @RequestMapping("/two")
    public String two();
    @RequestMapping("/oneByPage")
    public String oneByPage();
    @RequestMapping("/oneByPage2")
    public String oneByPage2();
    @RequestMapping("/insertOne")
    public String insertOne();
    @RequestMapping("/insertTwo")
    public String insertTwo();
    @RequestMapping("/redisInsert")
    public String redisInsert();
    @RequestMapping("/geoAdd")
    public String geoAdd(TestRedisRequestDTO testRedisRequestDTO);
    @RequestMapping("/geoRemove")
    public String geoRemove(TestRedisRequestDTO testRedisRequestDTO);
    @RequestMapping("/geoRadius")
    public String geoRadius(TestRedisRequestDTO testRedisRequestDTO);
    @RequestMapping("/geoDist")
    public String geoDist(TestRedisRequestDTO testRedisRequestDTO);
    @RequestMapping("/getNacos")
    public String getNacos();
}
