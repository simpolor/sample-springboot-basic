package io.simpolor.conditional.service;

import io.simpolor.conditional.repository.StudentRepository;
import io.simpolor.conditional.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student get(Long studentId) {

        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("studentId : "+studentId);
        }
        return optionalStudent.get();
    }

    public Student create(Student student) {

        return studentRepository.save(student);
    }

    public void update(Student student) {

        studentRepository.findById(student.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("studentId : "+student.getStudentId()));

        studentRepository.save(student);
    }

    public void delete(Long studentId) {
        studentRepository.deleteById(studentId);
    }

}
