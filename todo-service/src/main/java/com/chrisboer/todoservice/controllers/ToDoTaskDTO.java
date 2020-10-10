package com.chrisboer.todoservice.controllers;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ToDoTaskDTO {
    private long id;
    @NotBlank(message = "Task name cannot be blank")
    private String name;
    private String description;
    @JsonBackReference
    private TaskListDTO list;
}
