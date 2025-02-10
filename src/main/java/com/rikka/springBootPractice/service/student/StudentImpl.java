package com.rikka.springBootPractice.service.student;

import com.rikka.springBootPractice.model.dto.pet.PetDTO;
import com.rikka.springBootPractice.model.dto.student.StudentRequestDTO;
import com.rikka.springBootPractice.model.dto.student.StudentResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentImpl implements StudentService {

    @Override
    public StudentResponseDTO basicRes() {
        List<String> courseList = Arrays.asList("math", "english");
        List<PetDTO> petDTOList = new ArrayList<>();
        petDTOList.add(new PetDTO("white", "2"));
        petDTOList.add(new PetDTO("black", "3"));

        return new StudentResponseDTO("Amy", 19, false, 3.2, courseList, new PetDTO("white", "1"), petDTOList);
    }

    @Override
    public StudentResponseDTO basicRes(StudentRequestDTO req) {
        List<String> courseList = Arrays.asList("math", "english");
        List<PetDTO> petDTOList = new ArrayList<>();
        petDTOList.add(new PetDTO("white", "2"));
        petDTOList.add(new PetDTO("black", "3"));

        return new StudentResponseDTO(req.getName(), req.getAge(), false, 3.2, courseList, new PetDTO("white", "1"), petDTOList);
    }

    @Override
    public StudentResponseDTO exceptionPractice1() {
        throw new RuntimeException("RuntimeException");
    }

    @Override
    public StudentResponseDTO exceptionPractice2() {
        throw new IllegalArgumentException("IllegalArgumentException");
    }
}
