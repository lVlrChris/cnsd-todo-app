package com.chrisboer.todoservice.services;

import com.chrisboer.todoservice.models.Board;
import com.chrisboer.todoservice.models.TaskList;
import com.chrisboer.todoservice.models.ToDoTask;
import com.chrisboer.todoservice.repositories.ToDoTaskRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Service
public class ToDoTaskService {

    private final ToDoTaskRepository repo;
    private final BoardService boardService;

    public ToDoTaskService(ToDoTaskRepository repo,
                           BoardService boardService) {
        this.repo = repo;
        this.boardService = boardService;
    }

    public List<ToDoTask> findAll(long boardId, long listId) {
        Board foundBoard = boardService.findById(boardId);
        TaskList foundList = foundBoard.getLists().stream().filter(l -> l.getId() == listId).findFirst()
                .orElseThrow(EntityNotFoundException::new);
        return foundList.getTasks();
    }

    public ToDoTask findById(long id) {
        return repo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public ToDoTask createTask(@Valid ToDoTask toDoTask, long boardId, long listId) {
        Board foundBoard = boardService.findById(boardId);
        TaskList foundList = foundBoard.getLists().stream().filter(l -> l.getId() == listId).findFirst()
                .orElseThrow(EntityNotFoundException::new);
        toDoTask.setList(foundList);
        return repo.save(toDoTask);
    }

    public ToDoTask updateTask(long id, @Valid ToDoTask updatedTask, long boardId, long listId) {
        Board foundBoard = boardService.findById(boardId);
        TaskList foundList = foundBoard.getLists().stream().filter(l -> l.getId() == listId).findFirst()
                .orElseThrow(EntityNotFoundException::new);
        ToDoTask foundTask = foundList.getTasks().stream().filter(t -> t.getId() == id).findFirst()
                .orElseThrow(EntityNotFoundException::new);

        foundTask.setName(updatedTask.getName());
        foundTask.setDescription(updatedTask.getDescription());

        return repo.save(foundTask);
    }

    public void deleteTask(long id) {
        repo.deleteById(id);
    }
}
