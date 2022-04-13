package io.simpolor.eventlistener.controller;

import io.simpolor.eventlistener.listner.StudentEventListener;
import io.simpolor.eventlistener.listner.event.StudentSummaryEvent;
import io.simpolor.eventlistener.model.ResultDto;
import io.simpolor.eventlistener.model.StudentDto;
import io.simpolor.eventlistener.model.StudentSummaryDto;
import io.simpolor.eventlistener.repository.entity.Student;
import io.simpolor.eventlistener.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;
	private final StudentEventListener studentEventListener;

	@RequestMapping(value="", method=RequestMethod.GET)
	public List<StudentDto> list() {

		List<Student> students = studentService.getAll();
		if(CollectionUtils.isEmpty(students)){
			return Collections.EMPTY_LIST;
		}

		return StudentDto.of(students);
	}

	@RequestMapping(value="/summary", method=RequestMethod.GET)
	public List<StudentSummaryDto> summary() {

		List<StudentSummaryEvent> events = studentEventListener.getSummary();
		if(CollectionUtils.isEmpty(events)){
			return Collections.EMPTY_LIST;
		}

		return StudentSummaryDto.of(events);
	}

	@RequestMapping(value="/{studentId}", method=RequestMethod.GET)
	public StudentDto detail(@PathVariable Long studentId) {

		Student student = studentService.get(studentId);

		return StudentDto.of(student);
	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public ResultDto register(@RequestBody StudentDto request) {

		Student student = studentService.create(request.toEntity());

		return ResultDto.builder()
				.id(student.getStudentId())
				.build();
	}

	@RequestMapping(value="/{studentId}", method=RequestMethod.PUT)
	public void modify(@PathVariable Long studentId,
					   @RequestBody StudentDto request) {

		request.setId(studentId);
		studentService.update(request.toEntity());
	}

	@RequestMapping(value="/{studentId}", method=RequestMethod.DELETE)
	public void delete(@PathVariable Long studentId) {

		studentService.delete(studentId);
	}
}
