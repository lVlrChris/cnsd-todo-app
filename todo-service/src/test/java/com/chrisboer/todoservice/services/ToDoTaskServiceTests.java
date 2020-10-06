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
    }
}
