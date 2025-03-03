package com.projeto.saude_hub.controller.dto;

import com.projeto.saude_hub.domain.model.usuario.TipoSanguineo;
import com.projeto.saude_hub.domain.model.usuario.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record UsuarioDto(
        Long id,
        String nome,
        String email,
        String senha,
        LocalDate dataNascimento,
        String telefone,
        String endereco,
        TipoSanguineo tipoSanguineo,
        List<ConsultaDto> consultas,
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
                usuario.getConsultas().stream().map(ConsultaDto::new).toList(),
                usuario.getCriadoEm(),
                usuario.getAtualizadoEm()
        );
    }
}
