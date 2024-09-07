package com.example.simple_board.board.service;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.db.BoardRepository;
import com.example.simple_board.board.model.BoardDTO;
import com.example.simple_board.board.model.BoardRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardConverter boardConverter;

    public BoardDTO create(
            BoardRequest boardRequest
    ) {
        var entity = BoardEntity.builder()
                .boardName(boardRequest.getBoardName())
                .status("REGISTERED")
                .build();

        var boardEntity = boardRepository.save(entity);
        return boardConverter.toDto(boardEntity);
    }

    public BoardDTO view(long id) {
        var entity = boardRepository.findById(id).get(); // 임시 고정 무조건 있다 가정
        return boardConverter.toDto(entity);
    }
}
