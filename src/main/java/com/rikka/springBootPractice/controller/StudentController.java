package com.rikka.springBootPractice.controller;

import com.rikka.springBootPractice.model.dto.newStudent.NewStudentDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    @Qualifier("test1JdbcTemplate")
    private NamedParameterJdbcTemplate test1JdbcTemplate;

    @Autowired
    @Qualifier("test2JdbcTemplate")
    private NamedParameterJdbcTemplate test2JdbcTemplate;

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

}
