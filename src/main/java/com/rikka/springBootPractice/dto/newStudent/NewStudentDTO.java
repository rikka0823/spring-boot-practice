package com.rikka.springBootPractice.dto.newStudent;

public class NewStudentDTO {

    private Integer id;

    private String name;

    public NewStudentDTO() {
    }

    public NewStudentDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
