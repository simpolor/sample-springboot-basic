package io.simpolor.eventlistener.listner.event;

import lombok.Value;

@Value(staticConstructor = "of")
public class StudentSummaryEvent {

	private String name;
	private Integer grade;
}
