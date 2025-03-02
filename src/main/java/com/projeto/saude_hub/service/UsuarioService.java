package com.projeto.saude_hub.service;

import com.projeto.saude_hub.controller.dto.UsuarioDto;
import com.projeto.saude_hub.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario create(UsuarioDto usuarioDTO);
    Optional<UsuarioDto> findBydId(Long id);
    List<Usuario> findAll();
    Optional<UsuarioDto> update(Long id, UsuarioDto usuarioDto);
    void delete(Long id);
}
