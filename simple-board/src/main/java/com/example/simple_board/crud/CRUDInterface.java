package com.example.simple_board.crud;

import com.example.simple_board.common.Api;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CRUDInterface<T> {

    T create(T dto);

    Optional<T> read(Long id);

    T update(T dto);

    void delete(Long id);

    Api<List<T>> list(Pageable pageable);
}


