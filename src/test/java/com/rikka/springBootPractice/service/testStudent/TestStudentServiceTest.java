package com.rikka.springBootPractice.service.testStudent;

import com.rikka.springBootPractice.dao.testStudent.TestStudentDao;
import com.rikka.springBootPractice.model.dto.testStudent.TestStudentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class TestStudentServiceTest {
    // 可使用h2資料庫測試

    //    @MockitoBean
    @Autowired
//    @MockitoSpyBean
    private TestStudentService testStudentService;

    //    @MockitoBean
    @MockitoSpyBean
    private TestStudentDao testStudentDao;

//    @BeforeEach
//    public void beforeEach() {
//        TestStudentDTO mockStudent = new TestStudentDTO();
//        mockStudent.setName("amy");
//        mockStudent.setGraduate(false);
//        mockStudent.setScore(1.1);
//        mockStudent.setCreateDate(new Date());
//
//        Mockito.when(testStudentService.getById(Mockito.any())).thenReturn(mockStudent);
//    }

    @Test
    @Transactional(transactionManager = "practiceTransactionManage")
    public void insert() {
        TestStudentDTO testStudentDTO = new TestStudentDTO();
        testStudentDTO.setName("amy");
        testStudentDTO.setGraduate(false);
        testStudentDTO.setScore(1.1);
        Integer mockId = 3;

//        Mockito.when(testStudentDao.insert(testStudentDTO)).thenReturn(mockId);
//        Mockito.doReturn(mockId).when(testStudentService).insert(testStudentDTO);

        Integer id = testStudentDao.insert(testStudentDTO);
        System.out.println(id);
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

//        Mockito.when(testStudentService.insert(testStudentDTO)).thenReturn(1);

        Integer id = testStudentService.insert(testStudentDTO);
        System.out.println(id);
        testStudentDao.deleteById(id);

//        Mockito.when(testStudentService.getById(id)).thenReturn(null);

//        assertNull(testStudentService.getById(id));
        verify(testStudentDao, times(1)).deleteById(id);
    }

    @Test
    @Transactional(transactionManager = "practiceTransactionManage")
    public void update() {
        TestStudentDTO testStudentDTO = testStudentService.getById(1);
        testStudentDTO.setName("amy");
        testStudentDTO.setGraduate(false);
        testStudentDTO.setScore(1.1);

        testStudentDao.update(testStudentDTO);

        verify(testStudentDao, times(1)).update(testStudentDTO);

        assertNotNull(testStudentService.getById(1));
        assertEquals("amy", testStudentService.getById(1).getName());
        assertFalse(testStudentService.getById(1).isGraduate());
        assertEquals(1.1, testStudentService.getById(1).getScore());
        assertNotNull(testStudentService.getById(1).getCreateDate());
    }

    @Test
    @Transactional(transactionManager = "practiceTransactionManage")
    public void getById() {
        TestStudentDTO mockStudent = new TestStudentDTO();
        mockStudent.setName("Amy");
        mockStudent.setScore(90.3);
        mockStudent.setGraduate(true);
        mockStudent.setCreateDate(new Date());

        Mockito.when(testStudentDao.getById(Mockito.any())).thenReturn(mockStudent);

        TestStudentDTO testStudentDTO = testStudentService.getById(1);
        assertNotNull(testStudentDTO);
        assertEquals("Amy", testStudentDTO.getName());
        assertEquals(90.3, testStudentDTO.getScore());
        assertTrue(testStudentDTO.isGraduate());
        assertNotNull(testStudentDTO.getCreateDate());
    }
}