package com.rikka.springBootPractice.dao.testStudent;

import com.rikka.springBootPractice.mapper.TestStudentRowMapper;
import com.rikka.springBootPractice.model.dto.testStudent.TestStudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TestStudentDaoImpl implements TestStudentDao {

    @Autowired
    @Qualifier("practiceJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer insert(TestStudentDTO student) {
        String sql = "INSERT INTO test_student (name, score, graduate, create_date) VALUES (:name, :score, :graduate, :createDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("name", student.getName());
        map.put("score", student.getScore());
        map.put("graduate", student.isGraduate());
        map.put("createDate", new Date());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int id = keyHolder.getKey().intValue();
        System.out.println("mysql 自動生成的 id 為: " + id);

        return id;
    }

    @Override
    public void update(TestStudentDTO student) {
        String sql = "UPDATE test_student SET name = :name, score = :score, graduate = :graduate WHERE id = :id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", student.getId());
        map.put("name", student.getName());
        map.put("score", student.getScore());
        map.put("graduate", student.isGraduate());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM test_student WHERE id = :id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public TestStudentDTO getById(Integer id) {
        String sql = "SELECT id, name, score, graduate, create_date FROM test_student WHERE id = :id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        List<TestStudentDTO> list = namedParameterJdbcTemplate.query(sql, map, new TestStudentRowMapper());

        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
