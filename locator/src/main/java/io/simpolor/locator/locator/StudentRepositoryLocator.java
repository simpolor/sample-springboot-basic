package io.simpolor.locator.locator;

import io.simpolor.locator.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentRepositoryLocator {

    public final List<StudentRepository> studentRepositories;

    public StudentRepository getLocator(String key) {
        return studentRepositories.stream()
                .filter(api-> api.supports(key))
                .findFirst()
                .orElse(null);
    }
}
