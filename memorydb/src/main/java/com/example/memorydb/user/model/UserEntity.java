package com.example.memorydb.user.model;

import com.example.memorydb.entity.Entity;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity extends Entity {

    private String name;

    private int score;
}