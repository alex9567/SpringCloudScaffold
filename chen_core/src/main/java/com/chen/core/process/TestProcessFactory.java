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
    List<TsetProcessService> serviceList = new ArrayList<>();

    private static Map<String, TsetProcessService> serviceMap = new HashMap<String,TsetProcessService>();

    /**
     * 继承InitializingBean接口会在类注入后立刻进行操作，把具体的工厂类放入对应的map中
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if(serviceList==null){
            log.info("testProcessService null");
        }
        for(TsetProcessService service:serviceList){
            serviceMap.put(service.getType(),service);
        }
    }

    /**
     * 通过定义的方法名获得对应的执行类
     * @param type
     * @return
     */
    public TsetProcessService getProcessService(String type){
        return serviceMap.get(type);
    }

    /**
     * 执行多个类的方法，通过二进制状态机判断是否执行
     * @param num
     * @return
     */
    public List<TsetProcessService> getProcessServiceByInt(int num){
        List<TsetProcessService> returnList = new ArrayList<>();
        for (TsetProcessService service:serviceList) {
            int status = service.getNum();
            //通过二进制状态进行控制
            if((status&num)==status){
                returnList.add(service);
            }
        }
        return returnList;
    }
}
