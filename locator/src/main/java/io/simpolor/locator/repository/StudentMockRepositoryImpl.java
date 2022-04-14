package io.simpolor.locator.repository;

import io.simpolor.locator.repository.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class StudentMockRepositoryImpl implements StudentRepository {

    private final Student mock = new Student(1L, "홍길동", 2, 17, Arrays.asList("축구"));

    @Override
    public boolean supports(String s){
        return "mock".equalsIgnoreCase(s);
    }

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
