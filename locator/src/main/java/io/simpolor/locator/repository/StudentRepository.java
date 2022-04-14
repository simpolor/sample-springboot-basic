package io.simpolor.locator.repository;

import io.simpolor.locator.repository.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    boolean supports(String s);

    List<Student> findAll();
    Optional<Student> findById(Long studentId);
    Student save(Student student);
    void deleteById(Long studentId);
}
