package com.rikka.springBootPractice.controller;

import com.rikka.springBootPractice.model.dto.testStudent.TestStudentDTO;
import com.rikka.springBootPractice.service.testStudent.TestStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestStudentController {

    @Autowired
    private TestStudentService studentService;

    @PostMapping("/students")
    public ResponseEntity<TestStudentDTO> create(@RequestBody TestStudentDTO student) {

        Integer studentId = studentService.insert(student);

        TestStudentDTO newStudent = studentService.getById(studentId);

        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }

    @PutMapping("/students/{studentId}")
    public ResponseEntity<?> update(@PathVariable Integer studentId,
                                    @RequestBody TestStudentDTO student) {

        student.setId(studentId);
        studentService.update(student);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<?> delete(@PathVariable Integer studentId) {

        studentService.deleteById(studentId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<TestStudentDTO> read(@PathVariable Integer studentId) {

        TestStudentDTO student = studentService.getById(studentId);

        return ResponseEntity.status(HttpStatus.OK).body(student);
    }
}
