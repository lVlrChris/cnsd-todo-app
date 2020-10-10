package com.chrisboer.todoservice.services;

import com.chrisboer.todoservice.models.Board;
import com.chrisboer.todoservice.models.TaskList;
import com.chrisboer.todoservice.models.ToDoTask;
import com.chrisboer.todoservice.repositories.ToDoTaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

@ExtendWith(MockitoExtension.class)
public class ToDoTaskServiceTests {

    @Mock
    ToDoTaskRepository mockTaskRepo;
    @Mock
    BoardService mockBoardService;

    @InjectMocks
    ToDoTaskService service;

    @Test
    public void testGetAllTasks() {
        // Arrange
        Board parentBoard = new Board("Testboard");
        parentBoard.setId(1);

        TaskList parentList = new TaskList("Testlist", parentBoard);
        parentList.setId(1);
        List<TaskList> lists = new ArrayList<TaskList>();
        parentBoard.setLists(lists);
        parentBoard.getLists().add(parentList);

        List<ToDoTask> tasks = Arrays.asList(
                new ToDoTask("Task Name 1", parentList),
                new ToDoTask("Task Name 2", parentList),
                new ToDoTask("Task Name 3", parentList));
        parentList.setTasks(tasks);

//        Mockito.when(mockTaskRepo.findAll()).thenReturn(response);
        Mockito.when(mockBoardService.findById(Mockito.anyLong())).thenReturn(parentBoard);

        // Act
        List<ToDoTask> result = service.findAll(1, 1);

        // Assert
        assertThat(result).isNotEmpty();
        assertThat(result).isEqualTo(tasks);
        Mockito.verify(mockBoardService, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    public void testGetTaskById() {
        // Arrange
        Board parentBoard = new Board("Testboard");
        TaskList parentList = new TaskList("Testlist", parentBoard);

        ToDoTask response = new ToDoTask("TestName", parentList);

        Mockito.when(mockTaskRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(response));

        // Act
        ToDoTask result = service.findById(1);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(response);
        Mockito.verify(mockTaskRepo, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    public void testCreateTask() {
        // Arrange
        Board parentBoard = new Board("Testboard");
        parentBoard.setId(1);
        TaskList parentList = new TaskList("Testlist", parentBoard);
        parentList.setId(1);
        List<TaskList> lists = new ArrayList<TaskList>();
        parentBoard.setLists(lists);
        parentBoard.getLists().add(parentList);

        ToDoTask newTask = new ToDoTask("TestName", parentList);

        Mockito.when(mockTaskRepo.save(Mockito.any(ToDoTask.class))).then(returnsFirstArg());
        Mockito.when(mockBoardService.findById(Mockito.anyLong())).thenReturn(parentBoard);

        // Act
        ToDoTask result = service.createTask(newTask, parentBoard.getId(), parentList.getId());

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(newTask);
        Mockito.verify(mockTaskRepo, Mockito.times(1)).save(Mockito.any(ToDoTask.class));
        Mockito.verify(mockBoardService, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    public void testUpdateTask() {
        // Arrange
        Board parentBoard = new Board("Testboard");
        parentBoard.setId(1);

        TaskList parentList = new TaskList("Testlist", parentBoard);
        parentList.setId(1);
        List<TaskList> lists = new ArrayList<TaskList>();
        parentBoard.setLists(lists);
        parentBoard.getLists().add(parentList);

        ToDoTask oldTask = new ToDoTask("TestName", parentList);
        oldTask.setId(1);
        List<ToDoTask> tasks = new ArrayList<ToDoTask>();
        parentList.setTasks(tasks);
        parentList.getTasks().add(oldTask);

        ToDoTask updatedTask = new ToDoTask("NewTestName", parentList);
        updatedTask.setId(1);

        Mockito.when(mockBoardService.findById(Mockito.anyLong())).thenReturn(parentBoard);
        Mockito.when(mockTaskRepo.save(Mockito.any(ToDoTask.class))).then(returnsFirstArg());

        // Act
        ToDoTask result = service.updateTask(1L, updatedTask, parentBoard.getId(), parentList.getId());

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(updatedTask);
        Mockito.verify(mockTaskRepo, Mockito.times(1)).save(Mockito.any(ToDoTask.class));
        Mockito.verify(mockBoardService, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    public void testDeleteTask() {
        // Arrange
        Mockito.doNothing().when(mockTaskRepo).deleteById(Mockito.anyLong());

        // Act
        service.deleteTask(1);

        // Assert
        Mockito.verify(mockTaskRepo, Mockito.times(1)).deleteById(Mockito.anyLong());
    }
}
