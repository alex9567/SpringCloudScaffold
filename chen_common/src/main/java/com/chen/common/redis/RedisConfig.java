package com.chen.common.redis;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@NacosPropertySource(dataId = "db_config" ,groupId = "DEFAULT_GROUP", autoRefreshed = true)
@Slf4j
public class RedisConfig {

    @NacosValue(value = "${redis.host}",autoRefreshed = true)
    private String host;

    @NacosValue(value = "${redis.port}",autoRefreshed = true)
    private int port;

    @NacosValue(value = "${redis.password}",autoRefreshed = true)
    private String password;

    @NacosValue(value = "${redis.timeout}",autoRefreshed = true)
    private int timeout;

    @NacosValue(value = "${redis.maxIdle}",autoRefreshed = true)
    private int maxIdle;

    @NacosValue(value = "${redis.maxWaitMillis}",autoRefreshed = true)
    private int maxWaitMillis;

    @NacosValue(value = "${redis.blockWhenExhausted}",autoRefreshed = true)
    private Boolean blockWhenExhausted;

    @NacosValue(value = "${redis.JmxEnabled}",autoRefreshed = true)
    private Boolean JmxEnabled;

    @Bean
    public JedisPool jedisPoolFactory() {
        log.info("JedisPool注入开始...");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 连接耗尽时是否阻塞, false报异常,true阻塞直到超时, 默认true
        jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
        // 是否启用pool的jmx管理功能, 默认true
        jedisPoolConfig.setJmxEnabled(JmxEnabled);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        log.info("JedisPool注入成功...");
        return jedisPool;
    }
}
