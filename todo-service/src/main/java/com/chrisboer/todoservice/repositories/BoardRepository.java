package com.chrisboer.todoservice.repositories;

import com.chrisboer.todoservice.models.Board;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BoardRepository extends PagingAndSortingRepository<Board, Long> {
}
