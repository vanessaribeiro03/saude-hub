package com.projeto.saude_hub.service;

import com.projeto.saude_hub.controller.dto.ExameDto;
import com.projeto.saude_hub.domain.model.exame.Exame;

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
