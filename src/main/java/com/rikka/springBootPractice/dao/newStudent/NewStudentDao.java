package com.rikka.springBootPractice.dao.newStudent;

import com.rikka.springBootPractice.model.dto.newStudent.NewStudentDTO;

import java.util.List;
import java.util.Map;

public interface NewStudentDao {

    void saveStudent(NewStudentDTO req);

    void saveStudentWithAiSerialNumber(NewStudentDTO req);

    void saveAllStudents(List<NewStudentDTO> req);

    boolean existsById(Integer id);

    List<NewStudentDTO> getStudentById(Integer id);

    List<NewStudentDTO> getStudentsByIds(List<String> ids);

    List<NewStudentDTO> getAllStudents();

    Integer getStudentCount();

    void updateStudent(NewStudentDTO req);

    void deleteStudentById(Integer id);

    List<Map<String, Integer>> countStudentsByName();
}