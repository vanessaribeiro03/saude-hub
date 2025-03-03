package com.projeto.saude_hub.service;

import com.projeto.saude_hub.controller.dto.ConsultaDto;
import com.projeto.saude_hub.domain.model.consulta.Consulta;
import com.projeto.saude_hub.domain.model.consulta.StatusConsulta;

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
