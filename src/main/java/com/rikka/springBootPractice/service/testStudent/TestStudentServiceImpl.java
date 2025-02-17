package com.rikka.springBootPractice.service.testStudent;

import com.rikka.springBootPractice.dao.testStudent.TestStudentDao;
import com.rikka.springBootPractice.model.dto.testStudent.TestStudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestStudentServiceImpl implements TestStudentService {

    @Autowired
    private TestStudentDao studentDao;

    @Override
    public Integer insert(TestStudentDTO student) {
        return studentDao.insert(student);
    }

    @Override
    public void update(TestStudentDTO student) {
        studentDao.update(student);
    }

    @Override
    public void deleteById(Integer id) {
        studentDao.deleteById(id);
    }

    @Override
    public TestStudentDTO getById(Integer id) {
        return studentDao.getById(id);
    }
}
