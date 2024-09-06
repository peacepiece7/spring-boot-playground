package com.example.refactor_memory_db_to_mysql.book.db;


import com.example.refactor_memory_db_to_mysql.book.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, String> {

}
