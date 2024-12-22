package com.rikka.springBootPractice.service.impl;

import com.rikka.springBootPractice.component.Pet;
import com.rikka.springBootPractice.service.ifs.StudentService;
import com.rikka.springBootPractice.vo.StudentReq;
import com.rikka.springBootPractice.vo.StudentRes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentImpl implements StudentService {

    @Override
    public StudentRes basicRes() {
        List<String> courseList = Arrays.asList("math", "english");
        List<Pet> petList = new ArrayList<>();
        petList.add(new Pet("white", "2"));
        petList.add(new Pet("black", "3"));

        return new StudentRes("Amy", 19, false, 3.2, courseList, new Pet("white", "1"), petList);
    }

    @Override
    public StudentRes basicRes(StudentReq req) {
        List<String> courseList = Arrays.asList("math", "english");
        List<Pet> petList = new ArrayList<>();
        petList.add(new Pet("white", "2"));
        petList.add(new Pet("black", "3"));

        return new StudentRes(req.getName(), req.getAge(), false, 3.2, courseList, new Pet("white", "1"), petList);
    }
}
