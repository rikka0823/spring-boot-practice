package com.rikka.springBootPractice.vo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentReq {

    @Min(18)
    private Integer age;

    @NotBlank(message = "not blank")
    @Size(min = 2, max = 4)
    private String name;

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
