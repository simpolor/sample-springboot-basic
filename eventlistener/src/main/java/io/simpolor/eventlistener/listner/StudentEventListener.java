package io.simpolor.eventlistener.listner;

import io.simpolor.eventlistener.listner.event.StudentSummaryEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Slf4j
@Component
public class StudentEventListener {

    private static Queue<StudentSummaryEvent> queue = new LinkedList<>();

    @EventListener
    public void studentEventListener(StudentSummaryEvent event){

        log.info("Insert summary : {}, {}", event.getName(), event.getGrade());
        queue.offer(event);
    }

    public List<StudentSummaryEvent> getSummary(){

        List<StudentSummaryEvent> studentSummaryEvents = new ArrayList<>();
        for(StudentSummaryEvent event : queue){
            studentSummaryEvents.add(event);
        }

        return studentSummaryEvents;
    }
}
