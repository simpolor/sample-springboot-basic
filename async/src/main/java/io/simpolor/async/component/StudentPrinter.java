package io.simpolor.async.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StudentPrinter {

    private String defaultWord = "님께서 출력하셨습니다.";

    @Async
    public void printWord(String name){
        log.info(":::: StudentPrinter.printWord ::::"+name + defaultWord);
    }

    @Async("threadPoolTaskExecutor")
    public void printText(String name){
        log.info(":::: StudentPrinter.printText ::::"+name + defaultWord);
    }
}
