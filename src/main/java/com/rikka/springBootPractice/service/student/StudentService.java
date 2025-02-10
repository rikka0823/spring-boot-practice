package com.rikka.springBootPractice.service.student;

import com.rikka.springBootPractice.model.dto.student.StudentRequestDTO;
import com.rikka.springBootPractice.model.dto.student.StudentResponseDTO;

public interface StudentService {

    StudentResponseDTO basicRes();

    StudentResponseDTO basicRes(StudentRequestDTO req);

    StudentResponseDTO exceptionPractice1();

    StudentResponseDTO exceptionPractice2();
}
