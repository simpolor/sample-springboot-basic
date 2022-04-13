package io.simpolor.eventlistener.model;

import io.simpolor.eventlistener.listner.event.StudentSummaryEvent;
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
public class StudentSummaryDto {

	private String name;
	private Integer grade;

	public static StudentSummaryDto of(StudentSummaryEvent studentSummaryEvent){

		StudentSummaryDto studentSummaryDto = new StudentSummaryDto();
		studentSummaryDto.setName(studentSummaryEvent.getName());
		studentSummaryDto.setGrade(studentSummaryEvent.getGrade());

		return studentSummaryDto;
	}

	public static List<StudentSummaryDto> of(List<StudentSummaryEvent> studentSummaryEvents){

		return studentSummaryEvents.stream().map(StudentSummaryDto::of).collect(Collectors.toList());
	}

}
