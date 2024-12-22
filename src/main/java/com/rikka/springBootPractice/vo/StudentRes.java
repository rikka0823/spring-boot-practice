package com.rikka.springBootPractice.vo;

import com.rikka.springBootPractice.component.Pet;
import com.rikka.springBootPractice.component.Student;

import java.util.List;

public class StudentRes extends Student {

    public StudentRes() {
    }

    public StudentRes(String name, Integer age, Boolean edit, Double score, List<String> courseList, Pet pet, List<Pet> petList) {
        super(name, age, edit, score, courseList, pet, petList);
    }

}
