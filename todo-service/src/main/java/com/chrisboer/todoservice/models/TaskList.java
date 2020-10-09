package com.chrisboer.todoservice.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class TaskList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull @NotBlank(message = "List name cannot be blank")
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "list", cascade = CascadeType.REMOVE)
//    @JsonManagedReference
    private List<ToDoTask> tasks;
    @NonNull @NotNull(message = "List requires a parent board")
    @ManyToOne(optional = false)
    @JoinColumn(name = "board_id")
    @JsonManagedReference
    private Board board;
}
