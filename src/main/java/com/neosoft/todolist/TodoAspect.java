package com.neosoft.todolist;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TodoAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @AfterThrowing(value="execution(* com.neosoft.todolist.TodoController.*(..))",throwing="exception")
    public void afterThrowing(TodoListNotFoundException exception) {
        log.error(exception.toString());
    }
}
