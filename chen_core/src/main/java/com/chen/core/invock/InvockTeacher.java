package com.chen.core.invock;

public class InvockTeacher implements InvockPeople{
    @Override
    public String work() {
        System.out.println("老师教书育人...");
        return "教书";
    }

    @Override
    public String eat() {
        System.out.println("老师也要吃饭...");
        return "吃完了";
    }
}
