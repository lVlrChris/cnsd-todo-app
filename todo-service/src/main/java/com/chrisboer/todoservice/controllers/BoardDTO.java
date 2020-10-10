package com.chrisboer.todoservice.controllers;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class BoardDTO {
    private long id;
    @NotBlank(message = "Board name cannot be blank")
    private String name;
    private String description;
    @JsonManagedReference
    private List<TaskListDTO> lists;
}
