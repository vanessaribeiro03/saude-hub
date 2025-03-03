package com.projeto.saudehub.service;

import com.projeto.saudehub.controller.dto.ExameDto;
import com.projeto.saudehub.domain.model.exame.Exame;

import java.util.List;
import java.util.Optional;

public interface ExameService {
    Exame create(ExameDto exameDTO);
    Optional<ExameDto> findById(Long id);
    List<ExameDto> findAll();
    List<ExameDto> findByNome(String nome);
    Optional<ExameDto> update(Long id, ExameDto exameDTO);
    void delete(Long id);
}
