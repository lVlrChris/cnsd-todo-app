package com.chrisboer.todoservice.services;

import com.chrisboer.todoservice.models.Board;
import com.chrisboer.todoservice.repositories.BoardRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Service
public class BoardService {

    private final BoardRepository repo;

    public BoardService(BoardRepository repo) {
        this.repo = repo;
    }

    public List<Board> findAll() {
        return (List<Board>) repo.findAll();
    }

    public Board findById(long id) {
        return repo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Board createBoard(@Valid Board board) {
        return repo.save(board);
    }

    public Board updateBoard(long id, @Valid Board updatedBoard) {
        if (repo.existsById(id)) {
            return repo.save(updatedBoard);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void deleteBoard(long id) {
        repo.deleteById(id);
    }
}
