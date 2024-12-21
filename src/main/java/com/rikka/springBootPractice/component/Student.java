package com.rikka.springBootPractice.component;

import java.util.List;

public class Student {

    private String name;

    private Integer age;

    private Boolean edit;

    private Double score;

    private List<String> courseList;

    private Pet pet;

    private List<Pet> petList;

    public Student() {
    }

    public Student(String name, Integer age, Boolean edit, Double score, List<String> courseList, Pet pet, List<Pet> petList) {
        this.name = name;
        this.age = age;
        this.edit = edit;
        this.score = score;
        this.courseList = courseList;
        this.pet = pet;
        this.petList = petList;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Boolean getEdit() {
        return edit;
    }

    public Double getScore() {
        return score;
    }

    public List<String> getCourseList() {
        return courseList;
    }

    public Pet getPet() {
        return pet;
    }

    public List<Pet> getPetList() {
        return petList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", edit=" + edit +
                ", score=" + score +
                ", courseList=" + courseList +
                ", pet=" + pet +
                ", petList=" + petList +
                '}';
    }
}
