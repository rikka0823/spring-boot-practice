package com.rikka.springBootPractice.service.impl;

import com.rikka.springBootPractice.service.ifs.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
public class StudentImplTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void basicRes() {
        System.out.println(studentService.basicRes());
    }


    @Test
    public void test() {
        String str = "213";
        System.out.println(Integer.parseInt(str));
    }


}
