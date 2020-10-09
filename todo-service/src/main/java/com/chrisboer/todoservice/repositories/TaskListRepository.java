package com.chrisboer.todoservice.repositories;

import com.chrisboer.todoservice.models.Board;
import com.chrisboer.todoservice.models.TaskList;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TaskListRepository extends PagingAndSortingRepository<TaskList, Long> {

    //TODO: try @query here to fix recursion problem
    List<TaskList> findByBoard(Board board);
}
