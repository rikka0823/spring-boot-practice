package com.rikka.springBootPractice.mapper;

import com.rikka.springBootPractice.dto.newStudent.NewStudentDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewStudentMapper implements RowMapper<NewStudentDTO> {

    @Override
    public NewStudentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        NewStudentDTO newStudentDTO = new NewStudentDTO();
        newStudentDTO.setId(rs.getInt("id"));
        newStudentDTO.setName(rs.getString("name"));
        return newStudentDTO;
    }
}