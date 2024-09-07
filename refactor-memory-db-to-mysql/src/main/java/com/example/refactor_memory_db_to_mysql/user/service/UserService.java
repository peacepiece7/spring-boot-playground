package com.example.refactor_memory_db_to_mysql.user.service;

import com.example.refactor_memory_db_to_mysql.user.db.UserRepository;
import com.example.refactor_memory_db_to_mysql.user.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;




    public List<UserEntity> filterScore(int min, int max) {
        return userRepository.findAllByScoreGreaterThanEqualAndScoreLessThanEqual(min, max);
    }
}
