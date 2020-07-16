package com.chen.service.requestDTO;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author chenhao
 */
public class Test1RequestDTO implements Serializable {
    private static final long serialVersionUID = -4363227518432425298L;
    @NotBlank
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
