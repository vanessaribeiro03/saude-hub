package com.projeto.saudehub.service.impl;

import com.projeto.saudehub.controller.dto.ExameDto;
import com.projeto.saudehub.domain.model.exame.Exame;
import com.projeto.saudehub.domain.model.usuario.Usuario;
import com.projeto.saudehub.domain.repository.ExameRepository;
import com.projeto.saudehub.domain.repository.UsuarioRepository;
import com.projeto.saudehub.exceptions.CamposNulosException;
import com.projeto.saudehub.exceptions.exame.ExameNaoEncontradoException;
import com.projeto.saudehub.exceptions.usuario.UsuarioNaoEncontradoException;
import com.projeto.saudehub.infra.CampoUtil;
import com.projeto.saudehub.service.ExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExameServiceImpl implements ExameService {

    private final ExameRepository exameRepository;

    @Autowired
    public ExameServiceImpl(ExameRepository exameRepository) {
        this.exameRepository = exameRepository;
    }

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Exame create(ExameDto exameDTO) {
        List<String> camposObrigatorios = Arrays.asList( "nome", "dataExame", "local", "usuarioId");
        if(CampoUtil.hasCamposNulosGenerico(exameDTO, camposObrigatorios)){
            throw new CamposNulosException("Campos obrigatórios não podem ser nulos.");
        }

        Usuario usuario = usuarioRepository.findById(exameDTO.usuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado."));

        Exame exame = new Exame(
                null,
                exameDTO.nome(),
                exameDTO.dataExame(),
                exameDTO.local(),
                null,
                exameDTO.observacoes(),
                usuario,
                null,
                null
        );
        return exameRepository.save(exame);
    }

    @Override
    public Optional<ExameDto> findById(Long id) {
        Optional<Exame> exame = exameRepository.findById(id);

        if(exame.isEmpty()){
            throw new ExameNaoEncontradoException("Exame não encontrado.");
        }

        return exameRepository.findById(id).map(ExameDto::new);
    }

    @Override
    public List<ExameDto> findAll() {
        return exameRepository.findAll().stream()
                .map(ExameDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExameDto> findByNome(String nome) {
        return exameRepository.findByNomeContainingIgnoreCase(nome).stream()
                .map(ExameDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ExameDto> update(Long id, ExameDto exameDTO) {
        Exame exame = exameRepository.findById(id)
                .orElseThrow(() -> new ExameNaoEncontradoException("Usuário não encontrado."));

        exame.setNome(exameDTO.nome());
        exame.setDataExame(exameDTO.dataExame());
        exame.setLocal(exameDTO.local());
        exame.setResultado(exameDTO.resultado());
        exame.setObservacoes(exameDTO.observacoes());

        Exame exameAtualizado = exameRepository.save(exame);
        return Optional.of(new ExameDto(exameAtualizado));
    }

    @Override
    public void delete(Long id) {
        Optional<Exame> exame = exameRepository.findById(id);

        if(exame.isEmpty()){
            throw new ExameNaoEncontradoException("Exame não encontrado.");
        }

        exameRepository.deleteById(id);
    }
}
