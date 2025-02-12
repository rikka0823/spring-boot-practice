package com.rikka.springBootPractice.controller;

import com.rikka.springBootPractice.entity.Student;
import com.rikka.springBootPractice.model.dto.newStudent.NewStudentDTO;
import com.rikka.springBootPractice.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    @Qualifier("test1JdbcTemplate")
    private NamedParameterJdbcTemplate test1JdbcTemplate;

    @Autowired
    @Qualifier("test2JdbcTemplate")
    private NamedParameterJdbcTemplate test2JdbcTemplate;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/test1/students")
    public ResponseEntity<String> test1Insert(@Valid @RequestBody NewStudentDTO dto) {
        String sql = "INSERT INTO student (id, name) VALUES (:id, :name)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", dto.getId())
                .addValue("name", dto.getName());

        test1JdbcTemplate.update(sql, params);

        return ResponseEntity.status(HttpStatus.OK).body("insert data into test1");
    }

    @PostMapping("/test2/students")
    public ResponseEntity<String> test2Insert(@Valid @RequestBody NewStudentDTO dto) {
        String sql = "INSERT INTO student (id, name) VALUES (:id, :name)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", dto.getId())
                .addValue("name", dto.getName());

        test2JdbcTemplate.update(sql, params);

        return ResponseEntity.status(HttpStatus.OK).body("insert data into test2");
    }

    @PostMapping("/jpa/student/insert")
    public ResponseEntity<String> insertStudentByJpa(@Valid @RequestBody NewStudentDTO req) {
        Student student = new Student();
        student.setName(req.getName());
        studentRepository.save(student);

        return ResponseEntity.status(HttpStatus.OK).body("insert by jpa");
    }

    @GetMapping("/jpa/student/select/{id}")
    public ResponseEntity<Student> selectStudentByJpa(@PathVariable Integer id) {
        if (!studentRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(studentRepository.findById(id).get());
    }

    @PutMapping("/jpa/student/update/{id}")
    public ResponseEntity<String> updateStudentByJpa(@PathVariable Integer id,
                                                      @Valid @RequestBody NewStudentDTO req) {
        if (!studentRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Student student = studentRepository.findById(id).get();
        student.setName(req.getName());
        studentRepository.save(student);

        return ResponseEntity.status(HttpStatus.OK).body("200");
    }

    @DeleteMapping("/jpa/student/delete/{id}")
    public ResponseEntity<String> deleteStudentByJpa(@PathVariable Integer id) {
        if (!studentRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404");
        }

        studentRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("200");
    }

    @GetMapping("/jpa/student/byName/{name}")
    public ResponseEntity<List<Student>> findByName(@PathVariable String name) {
        System.out.println(name);
        System.out.println(studentRepository.findByName(name));
        return ResponseEntity.status(HttpStatus.OK).body(studentRepository.findByName(name));
    }

    @GetMapping("/jpa/student/byIdAndName")
    public ResponseEntity<Boolean> existsByIdAndName(@Valid @RequestBody NewStudentDTO studentDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(studentRepository.existsByIdAndName(studentDTO.getId(), studentDTO.getName()));
    }
}
