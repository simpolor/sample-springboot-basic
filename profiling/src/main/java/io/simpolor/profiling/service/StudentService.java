package io.simpolor.profiling.service;

import io.simpolor.profiling.interceptor.ProfileExecution;
import io.simpolor.profiling.repository.StudentRepository;
import io.simpolor.profiling.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @ProfileExecution
    public List<Student> getAll() {

        Iterable<Student> students = studentRepository.findAll();
        List<Student> list = new ArrayList<>();
        for(Student student : students){
            list.add(student);
        }

        return list;
    }

    @ProfileExecution
    public Student get(Long studentId) {

        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("studentId: "+studentId);
        }

        return optionalStudent.get();
    }

    @ProfileExecution
    public Student create(Student student) {

        return studentRepository.save(student);
    }

    @ProfileExecution
    public Student update(Student student) {

        Optional<Student> optionalStudent = studentRepository.findById(student.getStudentId());
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("studentId: "+student.getStudentId());
        }

        return studentRepository.save(student);
    }

    @ProfileExecution
    public Long delete(Long studentId) {

        studentRepository.deleteById(studentId);

        return studentId;
    }

}
