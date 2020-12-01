package com.chen.config.mq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ParamConfigService {
    @Value("${rocket.group}")
    public String rocketGroup ;
    @Value("${rocket.group2}")
    public String rocketGroup2 ;
    @Value("${rocket.topic}")
    public String rocketTopic ;
    @Value("${rocket.tag}")
    public String rocketTag ;
    @Value("${rocket.topic2}")
    public String rocketTopic2 ;
    @Value("${rocket.tag2}")
    public String rocketTag2 ;
}

