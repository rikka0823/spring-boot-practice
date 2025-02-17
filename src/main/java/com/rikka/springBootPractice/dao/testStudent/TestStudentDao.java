package com.rikka.springBootPractice.dao.testStudent;

import com.rikka.springBootPractice.model.dto.testStudent.TestStudentDTO;

public interface TestStudentDao {

    Integer insert(TestStudentDTO student);

    void update(TestStudentDTO student);

    void deleteById(Integer id);

    TestStudentDTO getById(Integer id);
}
