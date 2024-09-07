package com.example.simple_board.post.db;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.reply.db.ReplyEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long boardId;

    private String userName;

    private String password;

    private String email;

    private String status;

    private String title;

    @Column(columnDefinition = "TEXT") // 컬럼 정의가 다를 경우 명시해주기
    private String content;

    private LocalDateTime postedAt;

    @Builder.Default // Builder 패턴을 사용할 때 기본 값으로 List.of()를 사용하도록 설정
    @Transient // Entity 는 기본적으로 DB 컬럼으로 해석되기 때문에 예외 처리하려면 Transient 어노테이션 추가해줘야함
    private List<ReplyEntity> replyList = List.of();


}
