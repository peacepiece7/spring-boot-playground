package com.example.refactor_memory_db_to_mysql.user.db;

import com.example.refactor_memory_db_to_mysql.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
