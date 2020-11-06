package com.chrisboer.todoservice.controllers;

import com.chrisboer.todoservice.models.TaskList;
import com.chrisboer.todoservice.services.TaskListService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("api/v1/boards/{boardId}/lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final ModelMapper mapper;

    public TaskListController(TaskListService taskListService, ModelMapper mapper) {
        this.taskListService = taskListService;
        this.mapper = mapper;
    }

    @GetMapping("")
    public List<TaskListDTO> getAll(@PathVariable("boardId") long boardId) {
        return taskListService.findAll(boardId)
                .stream()
                .map(taskList -> mapper.map(taskList, TaskListDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TaskListDTO getTaskListById(@PathVariable("id") long id) {
        return mapper.map(taskListService.findById(id), TaskListDTO.class);
    }

    @PostMapping("")
    public TaskListDTO createTaskList(@Valid @RequestBody TaskListDTO newTaskList,
                                      @PathVariable("boardId") long boardId) {
        TaskList result = taskListService.createTaskList(mapper.map(newTaskList, TaskList.class), boardId);
        return mapper.map(result, TaskListDTO.class);
    }

    @PutMapping("/{id}")
    public TaskListDTO updateTaskList(@PathVariable("id") long id,
                                      @Valid @RequestBody TaskListDTO updatedTaskList,
                                      @PathVariable("boardId") long boardId) {
        TaskList result = taskListService.updateTaskList(id, mapper.map(updatedTaskList, TaskList.class), boardId);
        return mapper.map(result, TaskListDTO.class);
    }

    @DeleteMapping("/{id}")
    public void deleteTaskList(@PathVariable("id") long id) {
        taskListService.deleteTaskList(id);
    }
}
