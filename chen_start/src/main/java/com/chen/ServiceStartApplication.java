package com.chen;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
//Spring boot启动类
@EnableEurekaClient
//eureka 注册类
@RestController
//一个spring mvc控制
@EnableDiscoveryClient
//eureka 发现
@EnableFeignClients(basePackages = {"com.chen"})
//包的范围搜索
//@EnableTransactionManagement
//开启事务,只有只有单个数据源的时候可以用这个开启事务，使用XA2段提交之后不需要使用该注解了
public class ServiceStartApplication {
    public static void main(String[] args) {
        SpringApplication.run( ServiceStartApplication.class, args );
    }
    @Value("${server.port}")
    String port;
    @RequestMapping("/home")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }
}
