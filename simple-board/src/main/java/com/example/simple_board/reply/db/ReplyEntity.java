package com.example.simple_board.reply.db;

import com.example.simple_board.post.db.PostEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "reply")
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // @NotNull
    // private Long postId;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private PostEntity post;

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    private String status;

    @NotBlank
    private String title;

    @Column(columnDefinition = "TEXT") // 컬럼 정의가 다를 경우 명시해주기
    private String content;

    private LocalDateTime repliedAt;
}
