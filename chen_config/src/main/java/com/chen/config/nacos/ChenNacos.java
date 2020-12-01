package com.chen.config.nacos;

import java.io.Serializable;

public class ChenNacos implements Serializable {
    private static final long serialVersionUID = 1784978231968531666L;
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
