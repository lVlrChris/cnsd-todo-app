package com.chrisboer.todoservice.controllers;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class TaskListDTO {
    private long id;
    @NotBlank(message = "List name cannot be blank")
    private String name;
    @JsonManagedReference
    private List<ToDoTaskDTO> tasks;
    @JsonBackReference
    private BoardDTO board;
}
