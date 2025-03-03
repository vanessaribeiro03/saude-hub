package com.projeto.saudehub.service.impl;

import com.projeto.saudehub.controller.dto.ConsultaDto;
import com.projeto.saudehub.domain.model.consulta.Consulta;
import com.projeto.saudehub.domain.model.consulta.StatusConsulta;
import com.projeto.saudehub.domain.model.usuario.Usuario;
import com.projeto.saudehub.domain.repository.ConsultaRepository;
import com.projeto.saudehub.domain.repository.UsuarioRepository;
import com.projeto.saudehub.exceptions.CamposNulosException;
import com.projeto.saudehub.exceptions.consulta.ConsultaNaoEncontradaException;
import com.projeto.saudehub.exceptions.usuario.UsuarioNaoEncontradoException;
import com.projeto.saudehub.infra.CampoUtil;
import com.projeto.saudehub.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Consulta create(ConsultaDto consultaDTO) {
        List<String> camposObrigatorios = Arrays.asList( "dataConsulta", "especialidade", "medico", "status", "local", "usuarioId");
        if(CampoUtil.hasCamposNulosGenerico(consultaDTO, camposObrigatorios)){
            throw new CamposNulosException("Campos obrigatórios não podem ser nulos.");
        }

        Usuario usuario = usuarioRepository.findById(consultaDTO.usuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("teste")); // aqui

        Consulta consulta = new Consulta(
                null,
                consultaDTO.dataConsulta(),
                consultaDTO.especialidade(),
                consultaDTO.medico(),
                consultaDTO.status(),
                consultaDTO.observacoes(),
                consultaDTO.local(),
                usuario,
                null,
                null
        );

        return consultaRepository.save(consulta);
    }

    @Override
    public Optional<ConsultaDto> findById(Long id) {
        Optional<Consulta> consulta = consultaRepository.findById(id);

        if(consulta.isEmpty()){
            throw new ConsultaNaoEncontradaException("Consulta não encontrada.");
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
        Consulta consulta = consultaRepository.findById(id).orElseThrow(() -> new ConsultaNaoEncontradaException("Consulta não encontrada.")); // aqui

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
            throw new ConsultaNaoEncontradaException("Consulta não encontrada.");
        }

        consultaRepository.deleteById(id);
    }

}
