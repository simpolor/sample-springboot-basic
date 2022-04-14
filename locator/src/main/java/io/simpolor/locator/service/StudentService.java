package io.simpolor.locator.service;

import io.simpolor.locator.locator.StudentRepositoryLocator;
import io.simpolor.locator.repository.StudentRepository;
import io.simpolor.locator.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepositoryLocator studentRepositoryLocator;

    // private final String LOCATOR_KEY = "test";
    private final String LOCATOR_KEY = "mock";
    private StudentRepository studentRepository;

    @PostConstruct
    public void init(){
        studentRepository = studentRepositoryLocator.getLocator(LOCATOR_KEY);
    }

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
