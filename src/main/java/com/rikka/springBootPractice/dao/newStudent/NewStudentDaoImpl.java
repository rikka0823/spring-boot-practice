package com.rikka.springBootPractice.dao.newStudent;

import com.rikka.springBootPractice.model.dto.newStudent.NewStudentDTO;
import com.rikka.springBootPractice.mapper.NewStudentMapper;
import com.rikka.springBootPractice.model.vo.StudentNameCountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NewStudentDaoImpl implements NewStudentDao {

    @Autowired
    @Qualifier("practiceJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void saveStudent(NewStudentDTO req) {
        String sql = "INSERT INTO student (id, name) VALUES (:studentId, :studentName)";

        Map<String, Object> map = new HashMap<>();
        map.put("studentId", req.getId());
        map.put("studentName", req.getName());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void saveStudentWithAiSerialNumber(NewStudentDTO req) {
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
    }

    @Override
    public void saveAllStudents(List<NewStudentDTO> req) {
        String sql = "INSERT INTO student (name) VALUES (:studentName)";

        MapSqlParameterSource[] mapSqlParameterSource = new MapSqlParameterSource[req.size()];
        for (int i = 0; i < req.size(); i++) {
            NewStudentDTO newStudentDTO = req.get(i);
            mapSqlParameterSource[i] = new MapSqlParameterSource()
                    .addValue("studentName", newStudentDTO.getName());
        }

        namedParameterJdbcTemplate.batchUpdate(sql, mapSqlParameterSource);
    }

    @Override
    public boolean existsById(Integer id) {
        String sql = "SELECT EXISTS (SELECT 1 FROM student WHERE id = :id)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        return Boolean.TRUE.equals(namedParameterJdbcTemplate
                .queryForObject(sql, params, Boolean.class));
    }

    @Override
    public List<NewStudentDTO> getStudentById(Integer id) {
        String sql = "SELECT id, name FROM student WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id, Types.INTEGER);

        return namedParameterJdbcTemplate
                .query(sql, params, new NewStudentMapper());
    }

    @Override
    public List<NewStudentDTO> getStudentsByIds(List<String> ids) {
        String sql = "SELECT id, name FROM student WHERE id IN (:ids)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("ids", ids);

        return namedParameterJdbcTemplate.query(sql, params, new NewStudentMapper());
    }

    @Override
    public List<NewStudentDTO> getAllStudents() {
        String sql = "SELECT id, name FROM student";

        //        List<NewStudentDTO> studentDTOList = namedParameterJdbcTemplate.query(sql, new NewStudentMapper());

        List<NewStudentDTO> studentDTOList = namedParameterJdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    NewStudentDTO newStudentDTO = new NewStudentDTO();
                    newStudentDTO.setId(rs.getInt("id"));
                    newStudentDTO.setName(rs.getString("name"));
                    return newStudentDTO;
                });

        return studentDTOList;
    }

    @Override
    public Integer getStudentCount() {
        String sql = "SELECT COUNT(*) FROM student";

        Integer count = namedParameterJdbcTemplate.queryForObject(sql,
                new HashMap<>(), Integer.class);
        if (count == null) {
            count = 0;
        }

        return count;
    }

    @Override
    public void updateStudent(NewStudentDTO req) {
        String sql = "UPDATE student SET name = :studentName WHERE id = :studentId";

        Map<String, Object> map = new HashMap<>();
        map.put("studentName", req.getName());
        map.put("studentId", req.getId());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteStudentById(Integer id) {
        String sql = "DELETE FROM student WHERE id = :studentId";

        Map<String, Object> map = new HashMap<>();
        map.put("studentId", id);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public List<Map<String, Integer>> countStudentsByName() {
        String sql = "SELECT name, COUNT(id) AS count " +
                "FROM student " +
                "GROUP BY name";

        List<StudentNameCountVO> voList = namedParameterJdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    StudentNameCountVO vo = new StudentNameCountVO();
                    vo.setName(rs.getString("name"));
                    vo.setCount(rs.getInt("count"));
                    return vo;
                });
//        voList.forEach(System.out::println);

        List<Map<String, Integer>> voMapList = voList.stream()
                .map(vo -> {
                    Map<String, Integer> map = new HashMap<>();
                    map.put(vo.getName(), vo.getCount());
                    return map;
                })
                .toList();
//        voMapList.forEach(System.out::println);

        return voMapList;
    }
}