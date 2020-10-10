package com.chrisboer.todoservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Board name cannot be blank")
    @NonNull
    @Column(nullable = false)
    private String name;
    private String description;
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<TaskList> lists;
}
