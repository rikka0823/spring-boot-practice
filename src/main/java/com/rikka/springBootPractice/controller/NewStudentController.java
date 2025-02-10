package com.rikka.springBootPractice.controller;


import com.rikka.springBootPractice.model.dto.newStudent.NewStudentDTO;
import com.rikka.springBootPractice.service.newStudent.NewStudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;


@RequestMapping("/student")
@RestController
public class NewStudentController {

    @Autowired
    private NewStudentService newStudentService;

    @PostMapping("insert")
    public ResponseEntity<String> saveStudent(@Valid @RequestBody NewStudentDTO req) {
        return newStudentService.saveStudent(req);
    }

    @PostMapping("insertAi")
    public ResponseEntity<String> saveStudentWithAiSerialNumber(@RequestBody NewStudentDTO req) {
        return newStudentService.saveStudentWithAiSerialNumber(req);
    }

    @PostMapping("insertBatch")
    public ResponseEntity<String> saveAllStudents(@Valid @RequestBody List<NewStudentDTO> req) {
        return newStudentService.saveAllStudents(req);
    }

    @GetMapping("read/{id}")
    public ResponseEntity<NewStudentDTO> getStudentById(@PathVariable Integer id) {
        return newStudentService.getStudentById(id);
    }

    @GetMapping("read/searchStudents")
    public ResponseEntity<List<NewStudentDTO>> getStudentsByIds(@RequestBody List<String> ids) {
        return newStudentService.getStudentsByIds(ids);
    }

    @GetMapping("read/students")
    public ResponseEntity<List<NewStudentDTO>> getAllStudents() {
       return newStudentService.getAllStudents();
    }

    @GetMapping("read/studentCount")
    public ResponseEntity<Integer> getStudentCount() {
        return newStudentService.getStudentCount();
    }

    @PutMapping("update")
    public ResponseEntity<String> updateStudent(@Valid @RequestBody NewStudentDTO req) {
        return newStudentService.updateStudent(req);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Integer id) {
        return newStudentService.deleteStudentById(id);
    }

    @GetMapping("count")
    public ResponseEntity<List<Map<String, Integer>>> countStudentsByName() {
        return newStudentService.countStudentsByName();
    }
}