package com.chen.core.piplineHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
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
        } finally {
            pipeline.fireAfterCompletion();
        }
    }

    private Pipeline newPipeline(Request request) {
        DefaultPipeline pipeline = context.getBean(DefaultPipeline.class, request, new Task());
        return pipeline;
    }
}
