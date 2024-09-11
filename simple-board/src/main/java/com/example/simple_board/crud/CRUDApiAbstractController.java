package com.example.simple_board.crud;

import com.example.simple_board.common.Api;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class CRUDApiAbstractController<DTO, ENTITY> implements CRUDInterface<DTO> {


    @Autowired(required = false)
    private CRUDAbstractService<DTO, ENTITY> crudAbstractService;

    @Override
    @PostMapping("")
    public DTO create(
            @Valid
            @RequestBody
            DTO dto
    ) {
        return crudAbstractService.create(dto);
    }

    @Override
    @GetMapping("/id/{id}")
    public Optional<DTO> read(
            @PathVariable
            Long id
    ) {
        return crudAbstractService.read(id);
    }


    @Override
    @PutMapping("")
    public DTO update(
            @Valid
            @RequestBody
            DTO dto
    ) {
        return crudAbstractService.update(dto);
    }

    @Override
    @DeleteMapping("delete/{id}")
    public void delete(
            @PathVariable
            Long id
    ) {
        crudAbstractService.delete(id);
    }

    @Override
    @GetMapping("/all")
    public Api<List<DTO>> list(
            @PageableDefault
            Pageable pageable
    ) {
        return crudAbstractService.list(pageable);
    }
}
