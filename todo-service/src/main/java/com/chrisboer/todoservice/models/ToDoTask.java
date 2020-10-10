package com.chrisboer.todoservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ToDoTask {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull @NotBlank(message = "Task name cannot be blank")
    @Column(nullable = false)
    private String name;
    private String description;
    @NonNull @NotNull(message = "Task requires a parent list")
    @ManyToOne(optional = false)
    @JoinColumn(name = "task_list_id")
    private TaskList list;
}
