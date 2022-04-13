package io.simpolor.eventlistener.service;

import io.simpolor.eventlistener.listner.event.StudentSummaryEvent;
import io.simpolor.eventlistener.repository.StudentRepository;
import io.simpolor.eventlistener.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final ApplicationEventPublisher publisher;

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

        Student result = studentRepository.save(student);
        publisher.publishEvent(StudentSummaryEvent.of(student.getName(), student.getGrade()));

        return result;
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
