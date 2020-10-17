package com.chen.core.stream;

public class StreamStudent {
    private String name ;
    private int age;
    private int type;

    public StreamStudent() {
    }

    public StreamStudent(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public StreamStudent(String name, int age, int type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
