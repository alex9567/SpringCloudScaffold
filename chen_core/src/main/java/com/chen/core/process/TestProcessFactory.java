package com.chen.core.process;

import com.chen.core.process.service.TsetProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Component
public class TestProcessFactory implements InitializingBean {

    @Resource
    TsetProcessService[] testProcessService;

    private static Map<String, TsetProcessService> serviceMap = new HashMap<String,TsetProcessService>();

    private static List<TsetProcessService> serviceList = new ArrayList<>();
    @Override
    public void afterPropertiesSet() throws Exception {
        if(testProcessService==null){
            log.info("testProcessService null");
        }
        for(TsetProcessService service:testProcessService){
            serviceMap.put(service.getType(),service);
            serviceList.add(service);
        }
    }
    public TsetProcessService getProcessService(String type){
        return serviceMap.get(type);
    }
    public List<TsetProcessService> getProcessServiceByInt(int num){
        List<TsetProcessService> returnList = new ArrayList<>();
        for (TsetProcessService service:serviceList) {
            int status = service.getNum();
            if((status&num)==status){
                returnList.add(service);
            }
        }
        return returnList;
    }
}
