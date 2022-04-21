package io.simpolor.profiling.controller;

import io.simpolor.profiling.domain.ResultDto;
import io.simpolor.profiling.domain.StudentDto;
import io.simpolor.profiling.repository.entity.Student;
import io.simpolor.profiling.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/students")
@RestController
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@GetMapping
	public List<Student> list() {
		return studentService.getAll();
	}

	@GetMapping(value="/{studentId}")
	public Student detail(@PathVariable Long studentId) {
		return studentService.get(studentId);
	}

	@PostMapping
	public ResultDto register(@RequestBody StudentDto request) {

		Student student = studentService.create(request.toEntity());

		return ResultDto.of(student.getStudentId());
	}

	@PutMapping(value="/{studentId}")
	public void modify(@PathVariable Long studentId,
					   @RequestBody StudentDto request) {

		request.setId(studentId);
		studentService.update(request.toEntity());
	}

	@DeleteMapping(value="/{studentId}")
	public void delete(@PathVariable Long studentId) {

		studentService.delete(studentId);
	}

}
