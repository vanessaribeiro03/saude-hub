package com.projeto.saude_hub.service.impl;

import com.projeto.saude_hub.controller.dto.ConsultaDto;
import com.projeto.saude_hub.domain.model.consulta.Consulta;
import com.projeto.saude_hub.domain.model.consulta.StatusConsulta;
import com.projeto.saude_hub.domain.repository.ConsultaRepository;
import com.projeto.saude_hub.exceptions.consulta.CamposNulosConsultaException;
import com.projeto.saude_hub.exceptions.consulta.ConsultaNaoEncontradaException;
import com.projeto.saude_hub.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;

    @Autowired
    public ConsultaServiceImpl(ConsultaRepository consultaRepository){
        this.consultaRepository = consultaRepository;
    }

    @Override
    public Consulta create(ConsultaDto consultaDTO) {
        if(hasCamposNulos(consultaDTO)){
            throw new CamposNulosConsultaException(consultaDTO);
        }

        Consulta consulta = new Consulta(
                null,
                consultaDTO.dataConsulta(),
                consultaDTO.especialidade(),
                consultaDTO.medico(),
                consultaDTO.status(),
                consultaDTO.observacoes(),
                consultaDTO.local(),
                null,
                null
        );

        return consultaRepository.save(consulta);
    }

    @Override
    public Optional<ConsultaDto> findById(Long id) {
        Optional<Consulta> consulta = consultaRepository.findById(id);

        if(consulta.isEmpty()){
            throw new ConsultaNaoEncontradaException(id);
        }
        return consultaRepository.findById(id).map(ConsultaDto::new);
    }

    @Override
    public List<ConsultaDto> findAll() {
        return consultaRepository.findAll().stream()
                .map(ConsultaDto::new)
                .toList();
    }

    @Override
    public List<ConsultaDto> findByStatus(StatusConsulta status) {
        List<Consulta> consultas = consultaRepository.findByStatus(status);

        return consultas.stream().map(ConsultaDto::new).collect(Collectors.toList());
    }


    @Override
    public Optional<ConsultaDto> update(Long id, ConsultaDto consultaDto) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ConsultaNaoEncontradaException(id));

        consulta.setDataConsulta(consultaDto.dataConsulta());
        consulta.setEspecialidade(consultaDto.especialidade());
        consulta.setMedico(consultaDto.medico());
        consulta.setStatus(consultaDto.status());
        consulta.setObservacoes(consultaDto.observacoes());
        consulta.setLocal(consultaDto.local());

        Consulta consultaAtualizada = consultaRepository.save(consulta);

        return Optional.of(new ConsultaDto(consultaAtualizada));
    }

    @Override
    public void delete(Long id) {
        Optional<Consulta> consulta = consultaRepository.findById(id);

        if(consulta.isEmpty()){
            throw new ConsultaNaoEncontradaException(id);
        }

        consultaRepository.deleteById(id);
    }

    private boolean hasCamposNulos(ConsultaDto consultaDTO) {
        for (Field field : consultaDTO.getClass().getDeclaredFields()) {
            if (!CamposNulosConsultaException.getCamposObrigatorios().contains(field.getName())) {
                continue;
            }

            field.setAccessible(true);
            try {
                if (field.get(consultaDTO) == null) {
                    return true;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Erro ao verificar campos nulos.", e);
            }
        }
        return false;
    }

}
