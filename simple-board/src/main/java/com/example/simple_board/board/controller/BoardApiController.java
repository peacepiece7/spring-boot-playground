package com.example.simple_board.board.controller;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.model.BoardDTO;
import com.example.simple_board.board.model.BoardRequest;
import com.example.simple_board.board.service.BoardService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/board")
public class BoardApiController {

    private final BoardService boardService;

    @Transactional
    @PostMapping("")
    public BoardDTO create(
            @Valid
            @RequestBody
            BoardRequest boardRequest
    ) {
        return boardService.create(boardRequest);
    }


    @Transactional
    @GetMapping("/id/{id}")
    public BoardDTO view(
            @PathVariable long id
    ) {
        return boardService.view(id);
    }
}
