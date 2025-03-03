package com.projeto.saudehub.service;

import com.projeto.saudehub.controller.dto.MedicamentoDto;

import java.util.List;
import java.util.Optional;

public interface MedicamentoService {
    MedicamentoDto create(MedicamentoDto medicamentoDto);
    List<MedicamentoDto> findAll();
    Optional<MedicamentoDto> findById(Long id);
    List<MedicamentoDto> findByNome(String nome);
    Optional<MedicamentoDto> update(Long id, MedicamentoDto medicamentoDto);
    void delete(Long id);
}
