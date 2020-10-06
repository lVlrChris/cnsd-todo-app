package com.chrisboer.todoservice.services;

import com.chrisboer.todoservice.models.ToDoTask;
import com.chrisboer.todoservice.repositories.ToDoTaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

@ExtendWith(MockitoExtension.class)
public class ToDoTaskServiceTests {

    @Mock
    ToDoTaskRepository mockRepo;

    @InjectMocks
    ToDoTaskService service;

    @Test
    public void testGetAllTasks() {
        // Arrange
        List<ToDoTask> response = Arrays.asList(
                new ToDoTask("Task Name 1"),
                new ToDoTask("Task Name 2"),
                new ToDoTask("Task Name 3"));

        Mockito.when(mockRepo.findAll()).thenReturn(response);

        // Act
        List<ToDoTask> result = service.findAll();

        // Assert
        assertThat(result).isNotEmpty();
        assertThat(result).isEqualTo(response);
        Mockito.verify(mockRepo, Mockito.times(1)).findAll();
    }

    @Test
    public void testGetTaskById() {
        // Arrange
        ToDoTask response = new ToDoTask("TestName");

        Mockito.when(mockRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(response));

        // Act
        ToDoTask result = service.findById(1);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(response);
        Mockito.verify(mockRepo, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    public void testCreateTask() {
        // Arrange
        ToDoTask newTask = new ToDoTask("TestName");

        Mockito.when(mockRepo.save(Mockito.any(ToDoTask.class))).then(returnsFirstArg());

        // Act
        ToDoTask result = service.createTask(newTask);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(newTask);
        Mockito.verify(mockRepo, Mockito.times(1)).save(Mockito.any(ToDoTask.class));
    }

    @Test
    public void testUpdateTask() {
        // Arrange
        ToDoTask updatedTask = new ToDoTask("TestName");

        Mockito.when(mockRepo.save(Mockito.any(ToDoTask.class))).then(returnsFirstArg());
        Mockito.when(mockRepo.existsById(Mockito.anyLong())).thenReturn(true);

        // Act
        ToDoTask result = service.updateTask(1L, updatedTask);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(updatedTask);
        Mockito.verify(mockRepo, Mockito.times(1)).save(Mockito.any(ToDoTask.class));
    }

    @Test
    public void testDeleteTask() {
        // Arrange
        Mockito.doNothing().when(mockRepo).deleteById(Mockito.anyLong());

        // Act
        service.deleteTask(1);

        // Assert
        Mockito.verify(mockRepo, Mockito.times(1)).deleteById(Mockito.anyLong());
    }
}
