package com.chen.core.piplineHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApplicationService2 {

    @Autowired
    private ApplicationContext context;

    public void mockedClient(Request request) {
        //Request request = new Request();  // request一般是通过外部调用获取
        Pipeline pipeline = newPipeline(request);
        try {
            pipeline.fireOne();
            pipeline.fireTwo();
            pipeline.fireThree();
        }finally {
            log.info("fireAfterCompletion");
            pipeline.fireAfterCompletion();
        }
    }

    private Pipeline newPipeline(Request request) {
        DefaultPipeline pipeline = context.getBean(DefaultPipeline.class, request, new Task());
        return pipeline;
    }
}
