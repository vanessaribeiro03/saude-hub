package com.projeto.saude_hub.controller.dto;

import com.projeto.saude_hub.domain.model.TipoSanguineo;
import com.projeto.saude_hub.domain.model.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioDto(
        Long id,
        String nome,
        String email,
        String senha,
        LocalDate dataNascimento,
        String telefone,
        String endereco,
        TipoSanguineo tipoSanguineo,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {
    public UsuarioDto(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getDataNascimento(),
                usuario.getTelefone(),
                usuario.getEndereco(),
                usuario.getTipoSanguineo(),
                usuario.getCriadoEm(),
                usuario.getAtualizadoEm()
        );
    }
}

