package com.projeto.saudehub.service;

import com.projeto.saudehub.controller.dto.ConsultaDto;
import com.projeto.saudehub.domain.model.consulta.Consulta;
import com.projeto.saudehub.domain.model.consulta.StatusConsulta;

import java.util.List;
import java.util.Optional;

public interface ConsultaService {
    Consulta create(ConsultaDto consultaDTO);
    Optional<ConsultaDto> findById(Long id);
    List<ConsultaDto> findAll();
    Optional<ConsultaDto> update(Long id, ConsultaDto consultaDto);
    void delete(Long id);
    List<ConsultaDto> findByStatus(StatusConsulta status);
}
