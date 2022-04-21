package io.simpolor.profiling.domain;

import io.simpolor.profiling.repository.entity.Student;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

	private Long id;
	private String name;
	private Integer grade;
	private Integer age;
	private String hobby;

	public Student toEntity(){

		Student student = new Student();
		student.setStudentId(this.id);
		student.setName(this.name);
		student.setGrade(this.grade);
		student.setAge(this.age);
		student.setHobby(this.hobby);

		return student;
	}

	public static StudentDto of(Student student){

		StudentDto studentDto = new StudentDto();
		studentDto.setId(student.getStudentId());
		studentDto.setName(student.getName());
		studentDto.setGrade(student.getGrade());
		studentDto.setAge(student.getAge());
		studentDto.setHobby(student.getHobby());

		return studentDto;
	}

	public static List<StudentDto> of(List<Student> students){

		return students.stream().map(StudentDto::of).collect(Collectors.toList());
	}

}
