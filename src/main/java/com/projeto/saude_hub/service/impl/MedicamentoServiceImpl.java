package com.projeto.saude_hub.service.impl;

import com.projeto.saude_hub.controller.dto.ExameDto;
import com.projeto.saude_hub.controller.dto.MedicamentoDto;
import com.projeto.saude_hub.domain.model.exame.Exame;
import com.projeto.saude_hub.domain.model.medicamento.Medicamento;
import com.projeto.saude_hub.domain.model.usuario.Usuario;
import com.projeto.saude_hub.domain.repository.MedicamentoRepository;
import com.projeto.saude_hub.domain.repository.UsuarioRepository;
import com.projeto.saude_hub.exceptions.exame.CamposNulosExameException;
import com.projeto.saude_hub.exceptions.exame.ExameNaoEncontradoException;
import com.projeto.saude_hub.exceptions.medicamentos.CamposNulosMedicamentoException;
import com.projeto.saude_hub.exceptions.medicamentos.MedicamentoNaoEncontradoException;
import com.projeto.saude_hub.exceptions.usuario.UsuarioNaoEncontradoException;
import com.projeto.saude_hub.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    @Autowired
    public MedicamentoServiceImpl(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public MedicamentoDto create(MedicamentoDto dto) {
        if(hasCamposNulos(dto)){
            throw new CamposNulosMedicamentoException(dto);
        }

        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(dto.usuarioId()));

        Medicamento medicamento = new Medicamento(
                null,
                dto.nome(),
                dto.dosagem(),
                dto.periodo(),
                dto.viaAdministracao(),
                dto.instrucoesUso(),
                usuario,
                null,
                null
        );
        Medicamento salvo = medicamentoRepository.save(medicamento);
        return new MedicamentoDto(salvo);
    }

    @Override
    public List<MedicamentoDto> findAll() {
        List<Medicamento> medicamentos = medicamentoRepository.findAll();
        return medicamentos.stream()
                .map(MedicamentoDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MedicamentoDto> findById(Long id) {
        Optional<Medicamento> medicamento = medicamentoRepository.findById(id);

        if(medicamento.isEmpty()){
            throw new MedicamentoNaoEncontradoException(id);
        }

        return medicamentoRepository.findById(id)
                .map(MedicamentoDto::new);
    }

    @Override
    public List<MedicamentoDto> findByNome(String nome) {
        List<Medicamento> medicamentos = medicamentoRepository.findByNomeContainingIgnoreCase(nome);
        return medicamentos.stream()
                .map(MedicamentoDto::new)
                .toList();
    }

    @Override
    public Optional<MedicamentoDto> update(Long id, MedicamentoDto dto) {
        Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new MedicamentoNaoEncontradoException(id));

        medicamento.setNome(dto.nome());
        medicamento.setDosagem(dto.dosagem());
        medicamento.setPeriodo(dto.periodo());
        medicamento.setViaAdministracao(dto.viaAdministracao());
        medicamento.setInstrucoesUso(dto.instrucoesUso());

        Medicamento atualizado = medicamentoRepository.save(medicamento);
        return Optional.of(new MedicamentoDto(atualizado));
    }

    @Override
    public void delete(Long id) {
        Optional<Medicamento> medicamento = medicamentoRepository.findById(id);

        if(medicamento.isEmpty()){
            throw new MedicamentoNaoEncontradoException(id);
        }

        medicamentoRepository.deleteById(id);
    }

    private boolean hasCamposNulos(MedicamentoDto medicamentoDto) {
        for (Field field : medicamentoDto.getClass().getDeclaredFields()) {
            if (!CamposNulosMedicamentoException.getCamposObrigatorios().contains(field.getName())) {
                continue;
            }

            field.setAccessible(true);
            try {
                if (field.get(medicamentoDto) == null) {
                    return true;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Erro ao verificar campos nulos.", e);
            }
        }
        return false;
    }
}
