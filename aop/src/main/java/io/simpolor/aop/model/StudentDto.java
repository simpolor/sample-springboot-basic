package io.simpolor.aop.model;

import io.simpolor.aop.repository.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

	private Long id;
	private String name;
	private Integer grade;
	private Integer age;
	private List<String> hobbies;

	public Student toEntity(){

		Student student = new Student();
		student.setStudentId(this.id);
		student.setName(this.name);
		student.setGrade(this.grade);
		student.setAge(this.age);
		student.setHobbies(this.hobbies);

		return student;
	}

	public static StudentDto of(Student student){

		StudentDto studentDto = new StudentDto();
		studentDto.setId(student.getStudentId());
		studentDto.setName(student.getName());
		studentDto.setGrade(student.getGrade());
		studentDto.setAge(student.getAge());
		studentDto.setHobbies(student.getHobbies());

		return studentDto;
	}

	public static List<StudentDto> of(List<Student> students){

		return students.stream().map(StudentDto::of).collect(Collectors.toList());
	}

}
