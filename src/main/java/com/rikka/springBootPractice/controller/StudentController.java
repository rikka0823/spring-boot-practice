package com.rikka.springBootPractice.controller;


import com.rikka.springBootPractice.dto.newStudent.NewStudentDTO;
import com.rikka.springBootPractice.mapper.NewStudentMapper;
import com.rikka.springBootPractice.model.StudentNameCountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("/student")
@RestController
public class StudentController {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PostMapping("insert")
    public ResponseEntity<String> insertStudent(@RequestBody NewStudentDTO req) {
        String sql = "INSERT INTO student (id, name) VALUES (:studentId, :studentName)";

        Map<String, Object> map = new HashMap<>();
        map.put("studentId", req.getId());
        map.put("studentName", req.getName());

        namedParameterJdbcTemplate.update(sql, map);

        return ResponseEntity.status(HttpStatus.OK).body("200");
    }

    @PostMapping("insertAi")
    public ResponseEntity<String> insertStudentAi(@RequestBody NewStudentDTO req) {
        String sql = "INSERT INTO student (name) VALUES (:studentName)";

        Map<String, Object> map = new HashMap<>();
        map.put("studentName", req.getName());

//        MapSqlParameterSource params = new MapSqlParameterSource()
//                .addValue("studentName", req.getName());

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map),
                new GeneratedKeyHolder());

//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
//
//        int id = keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;
//        System.out.println(id);

        return ResponseEntity.status(HttpStatus.OK).body("200");
    }

    @PostMapping("insertBatch")
    public ResponseEntity<String> insertBatch(@RequestBody List<NewStudentDTO> req) {
        String sql = "INSERT INTO student (name) VALUES (:studentName)";

        MapSqlParameterSource[] mapSqlParameterSource = new MapSqlParameterSource[req.size()];
        for (int i = 0; i < req.size(); i++) {
            NewStudentDTO newStudentDTO = req.get(i);
            mapSqlParameterSource[i] = new MapSqlParameterSource()
                    .addValue("studentName", newStudentDTO.getName());
        }

        namedParameterJdbcTemplate.batchUpdate(sql, mapSqlParameterSource);

        return ResponseEntity.status(HttpStatus.OK).body("200");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
        String sql = "DELETE FROM student WHERE id = :studentId";

        Map<String, Object> map = new HashMap<>();
        map.put("studentId", id);

        namedParameterJdbcTemplate.update(sql, map);

        return ResponseEntity.status(HttpStatus.OK).body("200");
    }

    @GetMapping("read/{id}")
    public ResponseEntity<NewStudentDTO> searchStudent(@PathVariable Integer id) {
        String countSql = "SELECT COUNT(*) FROM student";

        Integer countId = namedParameterJdbcTemplate.queryForObject(countSql,
                new HashMap<>(), Integer.class);

        System.out.println(countId);

        String nameCountSql = "SELECT name, COUNT(id) AS count " +
                "FROM student " +
                "GROUP BY name";

        List<StudentNameCountVO> voList = namedParameterJdbcTemplate.query(nameCountSql,
                (rs, rowNum) -> {
                    StudentNameCountVO vo = new StudentNameCountVO();
                    vo.setName(rs.getString("name"));
                    vo.setCount(rs.getInt("count"));
                    return vo;
                });

        voList.forEach(System.out::println);

        List<Map<String, Integer>> voMapList = voList.stream()
                .map(vo -> {
                    Map<String, Integer> map = new HashMap<>();
                    map.put(vo.getName(), vo.getCount());
                    return map;
                })
                .toList();

        voMapList.forEach(System.out::println);

        String sql = "SELECT id, name FROM student WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id, Types.INTEGER);

        List<NewStudentDTO> newStudentDTO = namedParameterJdbcTemplate
                .query(sql, params, new NewStudentMapper());

        if (newStudentDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(newStudentDTO.get(0));
    }

    @GetMapping("read/searchStudents")
    public ResponseEntity<List<NewStudentDTO>> searchStudents(@RequestBody List<String> ids) {
        String sql = "SELECT id, name FROM student WHERE id IN (:ids)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("ids", ids);

        List<NewStudentDTO> studentDTOList = namedParameterJdbcTemplate.query(sql, params, new NewStudentMapper());

        return ResponseEntity.status(HttpStatus.OK).body(studentDTOList);
    }

    @GetMapping("read/students")
    public ResponseEntity<List<NewStudentDTO>> searchStudentList() {
        String sql = "SELECT id, name FROM student";

        //        List<NewStudentDTO> studentDTOList = namedParameterJdbcTemplate.query(sql, new NewStudentMapper());

        List<NewStudentDTO> studentDTOList = namedParameterJdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    NewStudentDTO newStudentDTO = new NewStudentDTO();
                    newStudentDTO.setId(rs.getInt("id"));
                    newStudentDTO.setName(rs.getString("name"));
                    return newStudentDTO;
                });

        return ResponseEntity.status(HttpStatus.OK).body(studentDTOList);
    }

    @PutMapping("update")
    public ResponseEntity<String> updateStudent(@RequestBody NewStudentDTO req) {
        String sql = "UPDATE student SET name = :studentName WHERE id = :studentId";

        Map<String, Object> map = new HashMap<>();
        map.put("studentName", req.getName());
        map.put("studentId", req.getId());

        namedParameterJdbcTemplate.update(sql, map);

        return ResponseEntity.status(HttpStatus.OK).body("200");
    }
}
