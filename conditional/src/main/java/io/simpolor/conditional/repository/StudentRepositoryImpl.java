package io.simpolor.conditional.repository;

import io.simpolor.conditional.repository.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Repository
@ConditionalOnProperty(name = "application.conditional.mock", havingValue = "false")
public class StudentRepositoryImpl implements StudentRepository {

    public static Long INDEX = 1L;
    public static Map<Long, Student> studentMap = new HashMap<>();

    public List<Student> findAll(){

        return studentMap.keySet().stream().map(key -> studentMap.get(key)).collect(Collectors.toList());
    }

    public Optional<Student> findById(Long studentId){

        return Optional.ofNullable(studentMap.get(studentId));
    }

    public Student save(Student student){

        if(Objects.isNull(student.getStudentId())){
            student.setStudentId(INDEX++);
        }
        studentMap.put(student.getStudentId(), student);

        return student;
    }

    public void deleteById(Long studentId){

        if(studentMap.containsKey(studentId)){
            studentMap.remove(studentId);
        }
    }
}
