package com.example.simple_board.board.model;

import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.model.PostDTO;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BoardDTO {

    private Long id;

    private String boardName;

    private String status;

    private List<PostDTO> postList = List.of();
}
