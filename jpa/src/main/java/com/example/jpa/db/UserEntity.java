package com.example.jpa.db;

import jakarta.persistence.*;
import lombok.*;



// @Data 메모리 이슈 있다고 뺴라함
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "user")
public class UserEntity {
    // 이래 관계를 맺는걸 ORM 이라 함
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MYSQL 에서 관리해줘서 IDENTITY 하라 함
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private Integer age;

    private String email;
}
