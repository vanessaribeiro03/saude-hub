package com.projeto.saude_hub.service.impl;

import com.projeto.saude_hub.controller.dto.ConsultaDto;
import com.projeto.saude_hub.controller.dto.ExameDto;
import com.projeto.saude_hub.domain.model.consulta.Consulta;
import com.projeto.saude_hub.domain.model.exame.Exame;
import com.projeto.saude_hub.domain.repository.ExameRepository;
import com.projeto.saude_hub.exceptions.consulta.CamposNulosConsultaException;
import com.projeto.saude_hub.exceptions.consulta.ConsultaNaoEncontradaException;
import com.projeto.saude_hub.exceptions.exame.CamposNulosExameException;
import com.projeto.saude_hub.exceptions.exame.ExameNaoEncontradoException;
import com.projeto.saude_hub.service.ExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
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

    @Override
    public Exame create(ExameDto exameDTO) {
        if(hasCamposNulos(exameDTO)){
            throw new CamposNulosExameException(exameDTO);
        }

        Exame exame = new Exame(
                null,
                exameDTO.nome(),
                exameDTO.dataExame(),
                exameDTO.local(),
                null,
                exameDTO.observacoes()
        );
        return exameRepository.save(exame);
    }

    @Override
    public Optional<ExameDto> findById(Long id) {
        Optional<Exame> exame = exameRepository.findById(id);

        if(exame.isEmpty()){
            throw new ExameNaoEncontradoException(id);
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
                .orElseThrow(() -> new ExameNaoEncontradoException(id));

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
            throw new ExameNaoEncontradoException(id);
        }

        exameRepository.deleteById(id);
    }

    private boolean hasCamposNulos(ExameDto exameDto) {
        for (Field field : exameDto.getClass().getDeclaredFields()) {
            if (!CamposNulosExameException.getCamposObrigatorios().contains(field.getName())) {
                continue;
            }

            field.setAccessible(true);
            try {
                if (field.get(exameDto) == null) {
                    return true;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Erro ao verificar campos nulos.", e);
            }
        }
        return false;
    }
}
