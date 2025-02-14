package com.rikka.springBootPractice.service.testStudent;

import com.rikka.springBootPractice.model.dto.testStudent.TestStudentDTO;

public interface TestStudentService {

    Integer insert(TestStudentDTO student);

    void update(TestStudentDTO student);

    void deleteById(Integer id);

    TestStudentDTO getById(Integer id);
}
