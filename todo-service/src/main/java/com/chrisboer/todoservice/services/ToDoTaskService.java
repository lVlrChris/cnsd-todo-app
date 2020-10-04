package com.chrisboer.todoservice.services;

import com.chrisboer.todoservice.models.ToDoTask;
import com.chrisboer.todoservice.repositories.ToDoTaskRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ToDoTaskService {

    private final ToDoTaskRepository repo;

    public ToDoTaskService(ToDoTaskRepository repo) {
        this.repo = repo;
    }

    public List<ToDoTask> findAll() {
        return (List<ToDoTask>)repo.findAll();
    }

    public ToDoTask findById(long id) {
        return repo.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
