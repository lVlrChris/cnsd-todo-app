package com.chrisboer.todoservice.controllers;

import com.chrisboer.todoservice.models.ToDoTask;
import com.chrisboer.todoservice.services.ToDoTaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// TODO: route tasks from correct resource
@RequestMapping("api/v1/tasks")
public class ToDoTaskController {

    private final ToDoTaskService toDoTaskService;

    public ToDoTaskController(ToDoTaskService toDoTaskService) {
        this.toDoTaskService = toDoTaskService;
    }

    @GetMapping("")
    public List<ToDoTask> getAll() {
        return toDoTaskService.findAll();
    }

    @GetMapping("/{id}")
    public ToDoTask getTaskById(@PathVariable("id")long id) {
        return toDoTaskService.findById(id);
    }

    @PostMapping
    public ToDoTask createTask()


}
