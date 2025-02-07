package com.rikka.springBootPractice.dto.student;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentRequestDTO {

    @Min(18)
    private Integer age;

    @NotBlank(message = "not blank")
    @Size(min = 2, max = 4)
    private String name;

    public StudentRequestDTO() {
    }

    public StudentRequestDTO(Integer age, String name) {
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
