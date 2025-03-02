package com.projeto.saude_hub.controller.dto;

import com.projeto.saude_hub.domain.model.consulta.StatusConsulta;
import com.projeto.saude_hub.domain.model.consulta.Consulta;

import java.time.LocalDateTime;

public record ConsultaDto(
        Long id,
        LocalDateTime dataConsulta,
        String especialidade,
        String medico,
        StatusConsulta status,
        String observacoes,
        String local,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {

    public ConsultaDto(Consulta consulta) {
        this(
                consulta.getId(),
                consulta.getDataConsulta(),
                consulta.getEspecialidade(),
                consulta.getMedico(),
                consulta.getStatus(),
                consulta.getObservacoes(),
                consulta.getLocal(),
                consulta.getCriadoEm(),
                consulta.getAtualizadoEm()
        );
    }

}
