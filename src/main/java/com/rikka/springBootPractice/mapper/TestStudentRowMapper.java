package com.rikka.springBootPractice.mapper;

import com.rikka.springBootPractice.model.dto.testStudent.TestStudentDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestStudentRowMapper implements RowMapper<TestStudentDTO> {

    @Override
    public TestStudentDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        TestStudentDTO student = new TestStudentDTO();
        student.setId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));
        student.setScore(resultSet.getDouble("score"));
        student.setGraduate(resultSet.getBoolean("graduate"));
        student.setCreateDate(resultSet.getTimestamp("create_date"));

        return student;
    }
}
