package io.simpolor.aop.aop;

import io.simpolor.aop.repository.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Aspect
@Component
public class StudentAspect {

    /*
    @Pointcut("execution(* io.simpolor.aop.service.StudentService.*(..))")
    public void pointcutStudent(){ }

    @Pointcut("* io.simpolor.aop.service.StudentService.registerStudent() &&" + "args(student, ..)")
    public void pointcutStudent(Student student){ }

    @Around("pointcutStudent()")
    */

    @Around("execution(* io.simpolor.aop.service.StudentService.*(..))")
    public Object checkStudent(ProceedingJoinPoint pjp) throws Throwable {

        log.info("Around start - {} / {}", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName());

        Student student = getStudent(pjp);
        if(Objects.nonNull(student)){
            log.info("student : {}" , student);
        }

        log.info("Around finished - {} / {}", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName());

        return pjp.proceed();
    }

    // @Before("execution(* io.simpolor.aop.service.StudentService.*(..))")
    @After("execution(* io.simpolor.aop.service.StudentService.*(..))")
    public void beforeStudent(JoinPoint joinPoint) {

        log.info("Before/After start - {} / {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());

        Student student = getStudent(joinPoint);
        if(Objects.nonNull(student)){
            log.info("Before student : {}" , student);
        }

        log.info("Before/After finished - {} / {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* io.simpolor.aop.service.StudentService.*(..))", throwing = "exception")
    public void afterThrowingStudent(JoinPoint joinPoint, Exception exception) {
        log.error("AfterThrowing error message : {}", exception.getMessage());
    }

    @AfterReturning(pointcut = "execution(* io.simpolor.aop.service.StudentService.*(..))", returning = "handler")
    public void afterReturningStudent(JoinPoint joinPoint, Object handler) {

        if(Objects.nonNull(handler)){
            log.info("AfterReturning value : "+handler);
        }

        if(handler instanceof Student){
            Student student = (Student) handler;
            student.setName(student.getName() + "_changed");
        }
    }

    @Around("@annotation(StudentCheck)")
    public Object studentCheck(ProceedingJoinPoint pjp) throws Throwable {

        log.info("Annotation checked - {} / {}", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName());

        return pjp.proceed();
    }

    private Student getStudent(ProceedingJoinPoint pjp){
        Student student = null;

        Object[] args = pjp.getArgs();
        for(Object o : args){
            if (o instanceof Student){
                student = (Student) o;
                break;
            }
        }
        return student;
    }

    private Student getStudent(JoinPoint joinPoint){
        Student student = null;

        Object[] args = joinPoint.getArgs();
        for(Object o : args){
            if (o instanceof Student){
                student = (Student) o;
                break;
            }
        }
        return student;
    }
}
