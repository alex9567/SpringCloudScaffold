package com.chen.service;

import com.chen.service.requestDTO.TestHelloRequestDTO;
import com.chen.service.requestDTO.TestRedisRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name = "SERVICE-A")
//@FeignClient注解value必须与服务客户端application.yml配置中服务命名对应
public interface TestService {
    @RequestMapping("/test/hello")
    public String hello(TestHelloRequestDTO requestDTO);
    @RequestMapping("/test/hi")
    public String hi();
    @RequestMapping("/test/one")
    public String one();
    @RequestMapping("/test/two")
    public String two();
    @RequestMapping("/test/oneByPage")
    public String oneByPage();
    @RequestMapping("/test/oneByPage2")
    public String oneByPage2();
    @RequestMapping("/test/insertOne")
    public String insertOne();
    @RequestMapping("/test/insertTwo")
    public String insertTwo();
    @RequestMapping("/test/redisInsert")
    public String redisInsert();
    @RequestMapping("/test/geoAdd")
    public String geoAdd(TestRedisRequestDTO testRedisRequestDTO);
    @RequestMapping("/test/geoRemove")
    public String geoRemove(TestRedisRequestDTO testRedisRequestDTO);
    @RequestMapping("/test/geoRadius")
    public String geoRadius(TestRedisRequestDTO testRedisRequestDTO);
    @RequestMapping("/test/geoDist")
    public String geoDist(TestRedisRequestDTO testRedisRequestDTO);
    @RequestMapping("/test/getNacos")
    public String getNacos();
}
