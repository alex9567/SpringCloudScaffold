package com.chen.service.requestDTO;

import java.io.Serializable;

public class TestHelloRequestDTO implements Serializable {
    private static final long serialVersionUID = -2311374311708243299L;
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestHelloRequestDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
