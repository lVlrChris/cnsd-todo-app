package com.chrisboer.todoservice.controllers;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class ToDoTaskDTO {
    private long id;
    @NotBlank(message = "Task name cannot be blank")
    private String name;
    private String description;
    @Setter(AccessLevel.NONE)
    private Date creationTime;
}
