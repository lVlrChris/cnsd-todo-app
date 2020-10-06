package com.chrisboer.todoservice.services;

import com.chrisboer.todoservice.models.ToDoTask;
import com.chrisboer.todoservice.repositories.ToDoTaskRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Service
public class ToDoTaskService {

    private final ToDoTaskRepository repo;

    public ToDoTaskService(ToDoTaskRepository repo) {
        this.repo = repo;
    }

    public List<ToDoTask> findAll() {
        return (List<ToDoTask>) repo.findAll();
    }

    public ToDoTask findById(long id) {
        return repo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public ToDoTask createTask(@Valid ToDoTask toDoTask) {
        return repo.save(toDoTask);
    }

    public ToDoTask updateTask(long id, @Valid ToDoTask updatedTask) {
        if (repo.existsById(id)) {
            return repo.save(updatedTask);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void deleteTask(long id) {
        repo.deleteById(id);
    }
}
