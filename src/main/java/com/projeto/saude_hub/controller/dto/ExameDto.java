package com.projeto.saude_hub.controller.dto;

import com.projeto.saude_hub.domain.model.exame.Exame;

import java.time.LocalDateTime;

public record ExameDto(
        Long id,
        String nome,
        LocalDateTime dataExame,
        String local,
        String resultado,
        String observacoes
) {
    public ExameDto(Exame exame) {
        this(
                exame.getId(),
                exame.getNome(),
                exame.getDataExame(),
                exame.getLocal(),
                exame.getResultado(),
                exame.getObservacoes()
        );
    }
}
