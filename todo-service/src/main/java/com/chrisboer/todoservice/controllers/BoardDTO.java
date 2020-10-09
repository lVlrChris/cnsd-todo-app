package com.chrisboer.todoservice.controllers;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class BoardDTO {
    private long id;
    @NotBlank(message = "Board name cannot be blank")
    private String name;
    private String description;
    private List<TaskListDTO> lists;
}
