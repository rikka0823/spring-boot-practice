package com.rikka.springBootPractice.model.dto.pet;

public class PetDTO {

    String name;

    String age;

    public PetDTO() {
    }

    public PetDTO(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
