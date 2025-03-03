package com.projeto.saudehub.controller.dto;

import com.projeto.saudehub.domain.model.medicamento.Medicamento;

import java.time.LocalDateTime;

public record MedicamentoDto(
        Long id,
        String nome,
        String dosagem,
        String periodo,
        String viaAdministracao,
        String instrucoesUso,
        Long usuarioId,
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
                medicamento.getUsuario().getId(),
                medicamento.getCriadoEm(),
                medicamento.getAtualizadoEm()
        );
    }
}
