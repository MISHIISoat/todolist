package com.neosoft.todolist;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping("todolist")
public class TodoController {
    TodoListRepository todoListRepository;

    public TodoController(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    @PostMapping
    ResponseEntity<URI> create(@Valid @RequestBody TodoList todoList) {
        TodoList save = todoListRepository.save(todoList);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .port(8080)
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    ResponseEntity<List<TodoList>> getAll() {
        return ResponseEntity.ok(todoListRepository.findAll());
    }

    @GetMapping("{id}")
    ResponseEntity<TodoList> getOne(@PathVariable Long id) throws TodoListNotFoundException {
        return ResponseEntity.ok(todoListRepository.findById(id).orElseThrow(() -> new TodoListNotFoundException(MessageFormat.format("Todo list with id {0} not found", id))));
    }


}
