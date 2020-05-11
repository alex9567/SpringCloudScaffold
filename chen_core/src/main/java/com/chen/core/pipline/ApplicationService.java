package com.chen.core.pipline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private List<Filter> filters;

    public void mockedClient() {
        Task task = new Task(); // 这里task一般是通过数据库查询得到的
        for (Filter filter : filters) {
            if (!filter.filter(task)) {
                return;
            }
        }
        // 过滤完成，后续是执行任务的逻辑
    }
}
