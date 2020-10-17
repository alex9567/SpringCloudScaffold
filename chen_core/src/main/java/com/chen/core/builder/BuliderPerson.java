package com.chen.core.builder;

public class BuliderPerson {

    private BuliderPerson(Builder builder) {
        name = builder.name;
        age = builder.age;
    }

    private String name;
    private int age;

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


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private int age;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public BuliderPerson build() {
            return new BuliderPerson(this);
        }
    }

    public static void main(String[] args) {
        BuliderPerson p = BuliderPerson.builder().age(1).name("1").build();
    }
}

