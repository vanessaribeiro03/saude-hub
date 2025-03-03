package com.projeto.saudehub.controller.dto;

import com.projeto.saudehub.domain.model.exame.Exame;

import java.time.LocalDateTime;

public record ExameDto(
        Long id,
        String nome,
        LocalDateTime dataExame,
        String local,
        String resultado,
        String observacoes,
        Long usuarioId,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {
    public ExameDto(Exame exame) {
        this(
                exame.getId(),
                exame.getNome(),
                exame.getDataExame(),
                exame.getLocal(),
                exame.getResultado(),
                exame.getObservacoes(),
                exame.getUsuario().getId(),
                exame.getCriadoEm(),
                exame.getAtualizadoEm()
        );
    }
}
