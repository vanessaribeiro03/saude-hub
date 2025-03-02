package com.projeto.saude_hub.service.impl;

import com.projeto.saude_hub.controller.dto.UsuarioDto;
import com.projeto.saude_hub.domain.model.Usuario;
import com.projeto.saude_hub.domain.repository.UsuarioRepository;
import com.projeto.saude_hub.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario create(UsuarioDto usuarioDTO) {
        Usuario usuario = new Usuario(
                null,
                usuarioDTO.nome(),
                usuarioDTO.email(),
                usuarioDTO.senha(),
                usuarioDTO.dataNascimento(),
                usuarioDTO.telefone(),
                usuarioDTO.endereco(),
                usuarioDTO.tipoSanguineo(),
                null,
                null
        );

        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<UsuarioDto> findBydId(Long id) {
        return usuarioRepository.findById(id).map(UsuarioDto::new);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<UsuarioDto> update(Long id, UsuarioDto usuarioDto) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(usuarioDto.nome());
        usuario.setEmail(usuarioDto.email());
        usuario.setSenha(usuarioDto.senha());
        usuario.setDataNascimento(usuarioDto.dataNascimento());
        usuario.setTelefone(usuarioDto.telefone());
        usuario.setEndereco(usuarioDto.endereco());
        usuario.setTipoSanguineo(usuarioDto.tipoSanguineo());

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);

        return Optional.of(new UsuarioDto(usuarioAtualizado));
    }

    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }
}
