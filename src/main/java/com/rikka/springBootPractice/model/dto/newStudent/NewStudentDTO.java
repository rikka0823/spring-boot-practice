package com.rikka.springBootPractice.model.dto.newStudent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NewStudentDTO {

    @NotNull
    private Integer id;

    @NotBlank
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
