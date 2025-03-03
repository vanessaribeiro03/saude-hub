package com.projeto.saude_hub.service.impl;

import com.projeto.saude_hub.controller.dto.UsuarioDto;
import com.projeto.saude_hub.domain.model.consulta.Consulta;
import com.projeto.saude_hub.domain.model.exame.Exame;
import com.projeto.saude_hub.domain.model.medicamento.Medicamento;
import com.projeto.saude_hub.domain.model.usuario.Usuario;
import com.projeto.saude_hub.domain.repository.UsuarioRepository;
import com.projeto.saude_hub.exceptions.CamposNulosException;
import com.projeto.saude_hub.exceptions.usuario.EmailExisteException;
import com.projeto.saude_hub.exceptions.usuario.UsuarioNaoEncontradoException;
import com.projeto.saude_hub.infra.CampoUtil;
import com.projeto.saude_hub.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
        List<String> camposObrigatorios = Arrays.asList( "nome", "email", "senha", "dataNascimento");
        if(CampoUtil.hasCamposNulosGenerico(usuarioDTO, camposObrigatorios)){
            throw new CamposNulosException("Campos obrigatórios não podem ser nulos.");
        }

        if(usuarioRepository.existsByEmail(usuarioDTO.email())){
            throw new EmailExisteException();
        }

        List<Consulta> consultas = (usuarioDTO.consultas() != null)
                ? usuarioDTO.consultas().stream()
                .map(dto -> new Consulta(
                        dto.id(),
                        dto.dataConsulta(),
                        dto.especialidade(),
                        dto.medico(),
                        dto.status(),
                        dto.observacoes(),
                        dto.local(),
                        null,
                        null,
                        null))
                .toList()
                : new ArrayList<>();

        List<Exame> exames = (usuarioDTO.exames() != null)
                ? usuarioDTO.exames().stream()
                .map(dto -> new Exame(
                        dto.id(),
                        dto.nome(),
                        dto.dataExame(),
                        dto.local(),
                        dto.resultado(),
                        dto.observacoes(),
                        null,
                        null,
                        null
                ))
                .toList()
                : new ArrayList<>();

        List<Medicamento> medicamentos = (usuarioDTO.medicamentos() != null)
                ? usuarioDTO.medicamentos().stream()
                .map(dto -> new Medicamento(
                        dto.id(),
                        dto.nome(),
                        dto.dosagem(),
                        dto.periodo(),
                        dto.viaAdministracao(),
                        dto.instrucoesUso(),
                        null,
                        null,
                        null
                ))
                .toList()
                : new ArrayList<>();

        Usuario usuario = new Usuario(
                null,
                usuarioDTO.nome(),
                usuarioDTO.email(),
                usuarioDTO.senha(),
                usuarioDTO.dataNascimento(),
                usuarioDTO.telefone(),
                usuarioDTO.endereco(),
                usuarioDTO.tipoSanguineo(),
                consultas,
                exames,
                medicamentos,
                null,
                null
        );

        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<UsuarioDto> findBydId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if(usuario.isEmpty()){
            throw new UsuarioNaoEncontradoException("Usuário não encontrado");
        }

        return usuario.map(UsuarioDto::new);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<UsuarioDto> update(Long id, UsuarioDto usuarioDto) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado."));

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
            throw new UsuarioNaoEncontradoException("Usuário não encontrado.");
        }

        usuarioRepository.deleteById(id);
    }

}
