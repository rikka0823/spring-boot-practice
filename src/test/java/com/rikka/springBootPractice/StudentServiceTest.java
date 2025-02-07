package com.rikka.springBootPractice;

import com.rikka.springBootPractice.service.student.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void basicRes() {
        System.out.println(studentService.basicRes());
    }
}