package com.example.jpa.db;

import org.springframework.data.jpa.repository.JpaRepository;


// Jpa 정의 들어가서 구현체 찾아보면 SimpleRepository 있음 이게 memoryDB 에서 만든 SimpleRepository 같은건데 JPA 에서 미리 만들어둠
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
