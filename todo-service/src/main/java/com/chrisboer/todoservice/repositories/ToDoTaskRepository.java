package com.chrisboer.todoservice.repositories;

import com.chrisboer.todoservice.models.ToDoTask;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ToDoTaskRepository extends PagingAndSortingRepository<ToDoTask, Long> {
}
