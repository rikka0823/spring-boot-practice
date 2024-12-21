package com.rikka.springBootPractice.component;

public class Pet {

    String name;

    String age;

    public Pet() {
    }

    public Pet(String name, String age) {
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
