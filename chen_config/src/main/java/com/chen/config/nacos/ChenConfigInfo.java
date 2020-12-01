package com.chen.config.nacos;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
@Slf4j
public class ChenConfigInfo extends AbstractConfigInfo<ChenNacos> {
    @Override
    protected String getDataId() {
        return "chen_nacos";
    }

    @Override
    protected void compile(String dataStr) {
        data = JSON.parseObject(dataStr, ChenNacos.class);
    }

}
