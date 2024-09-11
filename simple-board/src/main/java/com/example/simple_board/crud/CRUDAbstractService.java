package com.example.simple_board.crud;

import com.example.simple_board.common.Api;
import com.example.simple_board.common.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class CRUDAbstractService<DTO, ENTITY> implements CRUDInterface<DTO> {

    @Autowired(required = false)
    private JpaRepository<ENTITY, Long> jpaRepository;

    @Autowired(required = false)
    private Converter<DTO, ENTITY> converter;

    @Override
    public DTO create(DTO dto) {

        var entity = converter.toEntity(dto);

        jpaRepository.save(entity);

        return converter.toDto(entity);
    }

    @Override
    public Optional<DTO> read(Long id) {

        var optionalEntity = jpaRepository.findById(id);

        var optionalDto = optionalEntity
                .map(it -> converter.toDto(it))
                .orElseGet(() -> null);

        return Optional.ofNullable(optionalDto);
    }

    @Override
    public DTO update(DTO dto) {

        var entity = converter.toEntity(dto);
        jpaRepository.save(entity);

        return converter.toDto(entity); // 인자로 받은 dto 를 그대로 내려주는 거랑 크게 다른가..?
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Api<List<DTO>> list(Pageable pageable) {

        var list = jpaRepository.findAll(pageable);

        var pagination = Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .currentElements(list.getNumberOfElements())
                .totalElements(list.getTotalElements())
                .totalPage(list.getTotalPages())
                .build();

        var dtoList = list.stream()
                .map(converter::toDto)
                .toList();

        return Api.<List<DTO>>builder()
                .body(dtoList)
                .pagination(pagination)
                .build();
    }
}
