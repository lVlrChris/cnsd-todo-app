package com.chrisboer.todoservice.services;

import com.chrisboer.todoservice.models.Board;
import com.chrisboer.todoservice.models.TaskList;
import com.chrisboer.todoservice.repositories.TaskListRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Service
public class TaskListService {

    private final TaskListRepository repo;
    private final BoardService boardService;

    public TaskListService(TaskListRepository repo, BoardService boardService) {
        this.repo = repo;
        this.boardService = boardService;
    }

//    public List<TaskList> findAll() {
//        return (List<TaskList>) repo.findAll();
//    }

    public List<TaskList> findAll(long boardId) {
        Board foundBoard = boardService.findById(boardId);
        return repo.findByBoard(foundBoard);
    }

    public TaskList findById(long id) {
        return repo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public TaskList createTaskList(@Valid TaskList taskList, long boardId) {
        Board foundBoard = boardService.findById(boardId);
        taskList.setBoard(foundBoard);
        return repo.save(taskList);
    }

    public TaskList updateTaskList(long id, @Valid TaskList updatedTaskList) {
        if (repo.existsById(id)) {
            return repo.save(updatedTaskList);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void deleteTaskList(long id) {
        repo.deleteById(id);
    }
}
