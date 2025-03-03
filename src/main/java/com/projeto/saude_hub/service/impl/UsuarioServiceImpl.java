package com.projeto.saude_hub.service.impl;

import com.projeto.saude_hub.controller.dto.UsuarioDto;
import com.projeto.saude_hub.domain.model.usuario.Usuario;
import com.projeto.saude_hub.domain.repository.UsuarioRepository;
import com.projeto.saude_hub.exceptions.usuario.CamposNulosUsuarioException;
import com.projeto.saude_hub.exceptions.usuario.EmailExisteException;
import com.projeto.saude_hub.exceptions.usuario.UsuarioNaoEncontradoException;
import com.projeto.saude_hub.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
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
    public Usuario create(UsuarioDto usuarioDTO){
        if(hasCamposNulos(usuarioDTO)){
            throw new CamposNulosUsuarioException(usuarioDTO);
        }

        if(usuarioRepository.existsByEmail(usuarioDTO.email())){
            throw new EmailExisteException();
        }

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
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if(usuario.isEmpty()){
            throw new UsuarioNaoEncontradoException(id);
        }

        return usuario.map(UsuarioDto::new);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<UsuarioDto> update(Long id, UsuarioDto usuarioDto) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));

        if (usuarioRepository.existsByEmailAndIdNot(usuarioDto.email(), id)) {
            throw new EmailExisteException();
        }

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
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if(usuario.isEmpty()){
            throw new UsuarioNaoEncontradoException(id);
        }

        usuarioRepository.deleteById(id);
    }

    private boolean hasCamposNulos(UsuarioDto usuarioDTO) {
        for (Field field : usuarioDTO.getClass().getDeclaredFields()) {
            if (!CamposNulosUsuarioException.getCamposObrigatorios().contains(field.getName())) {
                continue;
            }

            field.setAccessible(true);
            try {
                if (field.get(usuarioDTO) == null) {
                    return true;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Erro ao verificar campos nulos", e);
            }
        }
        return false;
    }

}
