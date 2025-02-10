package com.rikka.springBootPractice.model.dto.student;

import com.rikka.springBootPractice.model.dto.pet.PetDTO;

import java.util.List;

public class StudentDTO {

    private String name;

    private Integer age;

    private Boolean edit;

    private Double score;

    private List<String> courseList;

    private PetDTO petDTO;

    private List<PetDTO> petDTOList;

    public StudentDTO() {
    }

    public StudentDTO(String name, Integer age, Boolean edit, Double score, List<String> courseList, PetDTO petDTO, List<PetDTO> petDTOList) {
        this.name = name;
        this.age = age;
        this.edit = edit;
        this.score = score;
        this.courseList = courseList;
        this.petDTO = petDTO;
        this.petDTOList = petDTOList;
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

    public PetDTO getPet() {
        return petDTO;
    }

    public List<PetDTO> getPetList() {
        return petDTOList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", edit=" + edit +
                ", score=" + score +
                ", courseList=" + courseList +
                ", pet=" + petDTO +
                ", petList=" + petDTOList +
                '}';
    }
}
