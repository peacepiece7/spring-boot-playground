package com.example.simple_board.post.db;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.reply.db.ReplyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

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

    @ManyToOne() // 자동으로 "이름+_id"를 DB 와 매핑 시킴, 즉 이름은 board 지만 db의 board_id가 외래키임을 알고 있음
    // @JoinColumn(name = "board_id") // 굳이 boardEntity 로 이름을 쓰고 싶으면 @JoinColumn 으로 조인해주면 됨
    @ToString.Exclude
    @JsonIgnore
    private BoardEntity board;

    private String userName;

    private String password;

    private String email;

    private String status;

    private String title;

    @Column(columnDefinition = "TEXT") // 컬럼 정의가 다를 경우 명시해주기
    private String content;

    private LocalDateTime postedAt;

    // @Builder.Default // Builder 패턴을 사용할 때 기본 값으로 List.of()를 사용하도록 설정
    // @Transient // Entity 는 기본적으로 DB 컬럼으로 해석되기 때문에 예외 처리하려면 Transient 어노테이션 추가해줘야함

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    @ToString.Exclude
    @Builder.Default
    private List<ReplyEntity> replyList = List.of();
}
