package com.chen.core.piplineHandler3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ChainConfig {

    @Resource
    private List<TestComponent> testComponents;

    @Bean
    public Test1Chain test1Chain() {
        List<TestComponent> chain = new ArrayList<>();

        chain.add(getComponent(OneTestComponent.class));
        chain.add(getComponent(TwoTestComponent.class));

        return new Test1Chain(chain);
    }

    @Bean
    public Test2Chain test2Chain() {
        List<TestComponent> chain = new ArrayList<>();

        chain.add(getComponent(OneTestComponent.class));
        chain.add(getComponent(ThreeTestComponent.class));
        chain.add(getComponent(FourTestComponent.class));

        return new Test2Chain(chain);
    }


    private <T extends TestComponent> T getComponent(Class<T> clazz) {
        for (TestComponent c : testComponents) {
            if (ClassUtils.isAssignable(c.getClass(), clazz)) {
                TestComponent testComponent = c;
                return testComponent == null ? null : (T) testComponent;
            }
        }
        return null;
    }
}