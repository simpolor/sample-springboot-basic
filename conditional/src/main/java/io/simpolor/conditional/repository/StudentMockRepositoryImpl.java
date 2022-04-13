package io.simpolor.conditional.repository;

import io.simpolor.conditional.repository.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Repository
@ConditionalOnProperty(name = "application.conditional.mock", havingValue = "true", matchIfMissing = true)
public class StudentMockRepositoryImpl implements StudentRepository {

    private final Student mock = new Student(1L, "홍길동", 2, 17, Arrays.asList("축구"));

    public List<Student> findAll(){

        return Arrays.asList(mock);
    }

    public Optional<Student> findById(Long studentId){

        return Optional.ofNullable(mock);
    }

    public Student save(Student student){

        log.debug("Save student");

        return mock;
    }

    public void deleteById(Long studentId){

        log.debug("Delete student");
    }
}
