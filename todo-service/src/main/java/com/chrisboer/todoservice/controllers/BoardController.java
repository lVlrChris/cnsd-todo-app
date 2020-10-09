package com.chrisboer.todoservice.controllers;

import com.chrisboer.todoservice.models.Board;
import com.chrisboer.todoservice.services.BoardService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/boards")
public class BoardController {

    private final BoardService boardService;
    private final ModelMapper mapper;

    public BoardController(BoardService boardService, ModelMapper mapper) {
        this.boardService = boardService;
        this.mapper = mapper;
    }

    @GetMapping("")
    public List<BoardDTO> getAll() {
        return boardService.findAll()
                .stream()
                .map(board -> mapper.map(board, BoardDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BoardDTO getBoardById(@PathVariable("id") long id) {
        return mapper.map(boardService.findById(id), BoardDTO.class);
    }

    @PostMapping("")
    public BoardDTO createBoard(@Valid @RequestBody BoardDTO newBoard) {
        Board result = boardService.createBoard(mapper.map(newBoard, Board.class));
        return mapper.map(result, BoardDTO.class);
    }

    @PutMapping("/{id}")
    public BoardDTO updateBoard(@PathVariable("id") long id, @Valid @RequestBody BoardDTO updatedBoard) {
        Board result = boardService.updateBoard(id, mapper.map(updatedBoard, Board.class));
        return mapper.map(result, BoardDTO.class);
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable("id") long id) {
        boardService.deleteBoard(id);
    }
}
