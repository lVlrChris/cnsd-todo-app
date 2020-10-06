package com.chrisboer.todoservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ToDoTask {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @NonNull
    private long id;
    @NonNull @NotBlank(message = "Task name cannot be blank")
    private String name;
    private String description;
}
