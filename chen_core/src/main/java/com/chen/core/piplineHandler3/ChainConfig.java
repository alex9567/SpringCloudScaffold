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
    private List<Component> refundComponents;

    @Bean
    public Test1Chain test1Chain() {
        List<Component> chain = new ArrayList<>();

        chain.add(getComponent(OneComponent.class));
        chain.add(getComponent(TwoComponent.class));

        return new Test1Chain(chain);
    }

    @Bean
    public Test2Chain test2Chain() {
        List<Component> chain = new ArrayList<>();

        chain.add(getComponent(OneComponent.class));
        chain.add(getComponent(ThreeComponent.class));
        chain.add(getComponent(FourComponent.class));

        return new Test2Chain(chain);
    }


    private <T extends Component> T getComponent(Class<T> clazz) {
        for (Component c : refundComponents) {
            if (ClassUtils.isAssignable(c.getClass(), clazz)) {
                Component component = c;
                return component == null ? null : (T) component;
            }
        }
        return null;
    }
}