package com.example.simple_board.board.db;

import com.example.simple_board.post.db.PostEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String boardName;

    private String status;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    @ToString.Exclude
    @Builder.Default
    // @Where() - deprecated
    // @Filter(name = "statusFilter", condition = "status = 'REGISTERED'")
    // @Where(clause = "status = 'REGISTERED'")
    @SQLOrder(value = "id desc")
    private List<PostEntity> postList = List.of();

}
