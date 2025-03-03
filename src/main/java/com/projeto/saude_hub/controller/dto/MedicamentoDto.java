package com.projeto.saude_hub.controller.dto;

import com.projeto.saude_hub.domain.model.medicamento.Medicamento;

import java.time.LocalDateTime;

public record MedicamentoDto(
        Long id,
        String nome,
        String dosagem,
        String periodo,
        String viaAdministracao,
        String instrucoesUso,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {
    public MedicamentoDto(Medicamento medicamento) {
        this(
                medicamento.getId(),
                medicamento.getNome(),
                medicamento.getDosagem(),
                medicamento.getPeriodo(),
                medicamento.getViaAdministracao(),
                medicamento.getInstrucoesUso(),
                medicamento.getCriadoEm(),
                medicamento.getAtualizadoEm()
        );
    }
}
