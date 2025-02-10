package com.rikka.springBootPractice.service.newStudent;

import com.rikka.springBootPractice.model.dto.newStudent.NewStudentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface NewStudentService {

    ResponseEntity<String> saveStudent(NewStudentDTO req);

    ResponseEntity<String> saveStudentWithAiSerialNumber(NewStudentDTO req);

    ResponseEntity<String> saveAllStudents(List<NewStudentDTO> req);

    boolean existsById(Integer id);

    ResponseEntity<NewStudentDTO> getStudentById(Integer id);

    ResponseEntity<List<NewStudentDTO>> getStudentsByIds(List<String> ids);

    ResponseEntity<List<NewStudentDTO>> getAllStudents();

    ResponseEntity<Integer> getStudentCount();

    ResponseEntity<String> updateStudent(NewStudentDTO req);

    ResponseEntity<String> deleteStudentById(Integer id);

    ResponseEntity<List<Map<String, Integer>>> countStudentsByName();
}