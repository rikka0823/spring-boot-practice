package com.rikka.springBootPractice.service.testStudent;

import com.rikka.springBootPractice.model.dto.testStudent.TestStudentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestStudentServiceTest {

    @Autowired
    private TestStudentService testStudentService;

    @Test
    @Transactional(transactionManager = "practiceTransactionManage")
    public void insert() {
        TestStudentDTO testStudentDTO = new TestStudentDTO();
        testStudentDTO.setName("amy");
        testStudentDTO.setGraduate(false);
        testStudentDTO.setScore(1.1);

        Integer id = testStudentService.insert(testStudentDTO);
        TestStudentDTO studentDTO = testStudentService.getById(id);

        assertNotNull(studentDTO);
        assertEquals("amy", studentDTO.getName());
        assertFalse(studentDTO.isGraduate());
        assertEquals(1.1, studentDTO.getScore());
        assertNotNull(studentDTO.getCreateDate());
    }

    @Test
    @Transactional(transactionManager = "practiceTransactionManage")
    public void deleteById() {
        TestStudentDTO testStudentDTO = new TestStudentDTO();
        testStudentDTO.setName("amy");
        testStudentDTO.setGraduate(false);
        testStudentDTO.setScore(1.1);

        Integer id = testStudentService.insert(testStudentDTO);

        testStudentService.deleteById(id);
        assertNull(testStudentService.getById(id));
    }

    @Test
    @Transactional(transactionManager = "practiceTransactionManage")
    public void update() {
        TestStudentDTO testStudentDTO = testStudentService.getById(1);
        testStudentDTO.setName("amy");
        testStudentDTO.setGraduate(false);
        testStudentDTO.setScore(1.1);

        testStudentService.update(testStudentDTO);

        assertNotNull(testStudentService.getById(1));
        assertEquals("amy", testStudentService.getById(1).getName());
        assertFalse(testStudentService.getById(1).isGraduate());
        assertEquals(1.1, testStudentService.getById(1).getScore());
        assertNotNull(testStudentService.getById(1).getCreateDate());
    }

    @Test
    @Transactional(transactionManager = "practiceTransactionManage")
    public void getById() {
        TestStudentDTO testStudentDTO = testStudentService.getById(1);
        assertNotNull(testStudentDTO);
        assertEquals("Amy", testStudentDTO.getName());
        assertEquals(90.3, testStudentDTO.getScore());
        assertTrue(testStudentDTO.isGraduate());
        assertNotNull(testStudentDTO.getCreateDate());
    }
}