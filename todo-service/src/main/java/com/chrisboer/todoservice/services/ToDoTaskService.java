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
    private final TaskListService taskListService;

    public ToDoTaskService(ToDoTaskRepository repo,
                           BoardService boardService,
                           TaskListService taskListService) {
        this.repo = repo;
        this.boardService = boardService;
        this.taskListService = taskListService;
    }

    //    public List<ToDoTask> findAll() {
//        return (List<ToDoTask>) repo.findAll();
//    }

    public List<ToDoTask> findAll(long boardId, long listId) {
        Board foundBoard = boardService.findById(boardId);
        TaskList foundList = foundBoard.getLists().stream().filter(l -> l.getId() == listId).findFirst()
                .orElseThrow(EntityNotFoundException::new);
        return foundList.getTasks();
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
