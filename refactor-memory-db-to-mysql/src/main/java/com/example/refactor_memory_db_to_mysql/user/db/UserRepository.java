package com.example.refactor_memory_db_to_mysql.user.db;

import com.example.refactor_memory_db_to_mysql.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public List<UserEntity> findAllByScoreGreaterThanEqual(int sc);

    List<UserEntity> findAllByScoreGreaterThanEqualAndScoreLessThanEqual(int min, int max);


    // 네이티브 쿼리 쓰기, jpql 방식
    @Query(
            "select u from user u where u.score >= ?1 AND score >= ?2" // ?1 => min, ?2 => max 에 매치됨
    )
    List<UserEntity> score(int min, int max);


    // 직접 native 쿼리 쓰기
    @Query(
            value = "select * from user as u where u.score >= :min AND u.score <= :max",
            nativeQuery = true
    )
    List<UserEntity> score2(
            @Param(value = "min") int min, // named parameter
            @Param(value = "max") int max);


    // 추천 순서, 쿼리 메소드 -> 복잡한 쿼리면 네이티브 쿼리
    // 네이티브 쿼리시 조금 복잡해서 named parameter 로 parameter binding 해서 쓰기
}

