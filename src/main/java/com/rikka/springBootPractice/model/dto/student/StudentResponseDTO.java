package com.rikka.springBootPractice.model.dto.student;

import com.rikka.springBootPractice.model.dto.pet.PetDTO;

import java.util.List;

public class StudentResponseDTO extends StudentDTO {

    public StudentResponseDTO() {
    }

    public StudentResponseDTO(String name, Integer age, Boolean edit, Double score, List<String> courseList, PetDTO petDTO, List<PetDTO> petDTOList) {
        super(name, age, edit, score, courseList, petDTO, petDTOList);
    }

}
