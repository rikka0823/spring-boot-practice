package com.rikka.springBootPractice.vo;

public class StudentReq {

    public Integer age;

    public String name;

    public StudentReq() {
    }

    public StudentReq(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "StudentReq{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
