package com.rikka.springBootPractice.service.newStudent;

import com.rikka.springBootPractice.dao.NewStudentDao;
import com.rikka.springBootPractice.dto.newStudent.NewStudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NewStudentServiceImpl implements NewStudentService {

    @Autowired private NewStudentDao newStudentDao;

    @Override
    public ResponseEntity<String> saveStudent(NewStudentDTO req) {
        newStudentDao.saveStudent(req);
        return ResponseEntity.status(HttpStatus.OK).body("200");
    }

    @Override
    public ResponseEntity<String> saveStudentWithAiSerialNumber(NewStudentDTO req) {
        newStudentDao.saveStudentWithAiSerialNumber(req);
        return ResponseEntity.status(HttpStatus.OK).body("200");
    }

    @Override
    public ResponseEntity<String> saveAllStudents(List<NewStudentDTO> req) {
        newStudentDao.saveAllStudents(req);
        return ResponseEntity.status(HttpStatus.OK).body("200");
    }

    @Override
    public boolean existsById(Integer id) {
        return newStudentDao.existsById(id);
    }

    @Override
    public ResponseEntity<NewStudentDTO> getStudentById(Integer id) {
        List<NewStudentDTO> newStudentDTO = newStudentDao.getStudentById(id);

        if (newStudentDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(newStudentDTO.get(0));
    }

    @Override
    public ResponseEntity<List<NewStudentDTO>> getStudentsByIds(List<String> ids) {
        List<NewStudentDTO> studentDTOList = newStudentDao.getStudentsByIds(ids);

        if (studentDTOList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }

        return ResponseEntity.status(HttpStatus.OK).body(studentDTOList);
    }

    @Override
    public ResponseEntity<List<NewStudentDTO>> getAllStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(newStudentDao.getAllStudents());
    }

    @Override
    public ResponseEntity<Integer> getStudentCount() {
        return ResponseEntity.status(HttpStatus.OK).body(newStudentDao.getStudentCount());
    }

    @Override
    public ResponseEntity<String> updateStudent(NewStudentDTO req) {
        if (!existsById(req.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404");
        }

        newStudentDao.updateStudent(req);

        return ResponseEntity.status(HttpStatus.OK).body("200");
    }

    @Override
    public ResponseEntity<String> deleteStudentById(Integer id) {
        if (!existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404");
        }

        newStudentDao.deleteStudentById(id);

        return ResponseEntity.status(HttpStatus.OK).body("200");
    }

    @Override
    public ResponseEntity<List<Map<String, Integer>>> countStudentsByName() {
        return ResponseEntity.status(HttpStatus.OK).body(newStudentDao.countStudentsByName());
    }
}