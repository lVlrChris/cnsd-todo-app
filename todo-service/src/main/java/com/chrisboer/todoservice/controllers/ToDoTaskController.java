package com.chrisboer.todoservice.controllers;

import com.chrisboer.todoservice.models.ToDoTask;
import com.chrisboer.todoservice.services.ToDoTaskService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("api/v1/boards/{boardId}/lists/{listId}/tasks")
public class ToDoTaskController {

    private final ToDoTaskService toDoTaskService;
    private final ModelMapper mapper;

    public ToDoTaskController(ToDoTaskService toDoTaskService, ModelMapper mapper) {
        this.toDoTaskService = toDoTaskService;
        this.mapper = mapper;
    }

    @GetMapping("")
    public List<ToDoTaskDTO> getAll(@PathVariable("boardId") long boardId,
                                    @PathVariable("listId") long listId) {
        return toDoTaskService.findAll(boardId, listId)
                .stream()
                .map(task -> mapper.map(task, ToDoTaskDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ToDoTaskDTO getTaskById(@PathVariable("id") long id) {
        return mapper.map(toDoTaskService.findById(id), ToDoTaskDTO.class);
    }

    @PostMapping("")
    public ToDoTaskDTO createTask(@Valid @RequestBody ToDoTaskDTO newTask,
                                  @PathVariable("boardId") long boardId,
                                  @PathVariable("listId") long listId) {
        ToDoTask result = toDoTaskService.createTask(mapper.map(newTask, ToDoTask.class), boardId, listId);
        return mapper.map(result, ToDoTaskDTO.class);
    }

    @PutMapping("/{id}")
    public ToDoTaskDTO updateTask(@PathVariable("id") long id,
                                  @Valid @RequestBody ToDoTaskDTO updatedTask,
                                  @PathVariable("boardId") long boardId,
                                  @PathVariable("listId") long listId) {
        ToDoTask result = toDoTaskService.updateTask(id, mapper.map(updatedTask, ToDoTask.class), boardId, listId);
        return mapper.map(result, ToDoTaskDTO.class);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") long id) {
        toDoTaskService.deleteTask(id);
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(EntityNotFoundException.class)
//    public Map<String, String> handleEntityNotFoundExceptions(
//            EntityNotFoundException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }

}
