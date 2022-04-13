package io.simpolor.conditional.repository;

import io.simpolor.conditional.repository.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    List<Student> findAll();
    Optional<Student> findById(Long studentId);
    Student save(Student student);
    void deleteById(Long studentId);
}
