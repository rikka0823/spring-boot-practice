package com.rikka.springBootPractice.repository;

import com.rikka.springBootPractice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByName(String name);

    boolean existsByIdAndName(Integer id, String name);
}
