package com.chrisboer.todoservice.controllers;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class TaskListDTO {
    private long id;
    @NotBlank(message = "List name cannot be blank")
    private String name;
    private List<ToDoTaskDTO> tasks;
    private BoardDTO board;
}
