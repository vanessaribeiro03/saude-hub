package com.projeto.saude_hub.service.impl;

import com.projeto.saude_hub.controller.dto.MedicamentoDto;
import com.projeto.saude_hub.domain.model.medicamento.Medicamento;
import com.projeto.saude_hub.domain.model.usuario.Usuario;
import com.projeto.saude_hub.domain.repository.MedicamentoRepository;
import com.projeto.saude_hub.domain.repository.UsuarioRepository;
import com.projeto.saude_hub.exceptions.CamposNulosException;
import com.projeto.saude_hub.exceptions.medicamentos.MedicamentoNaoEncontradoException;
import com.projeto.saude_hub.exceptions.usuario.UsuarioNaoEncontradoException;
import com.projeto.saude_hub.infra.CampoUtil;
import com.projeto.saude_hub.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
        List<String> camposObrigatorios = Arrays.asList(  "nome", "dosagem", "periodo", "usuarioId");
        if(CampoUtil.hasCamposNulosGenerico(dto, camposObrigatorios)){
            throw new CamposNulosException("Campos obrigatórios não podem ser nulos.");
        }

        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado."));

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
            throw new MedicamentoNaoEncontradoException("Medicamento não encontrado.");
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
                .orElseThrow(() -> new MedicamentoNaoEncontradoException("Medicamento não encontrado."));

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
            throw new MedicamentoNaoEncontradoException("Medicamento não encontrado.");
        }

        medicamentoRepository.deleteById(id);
    }

}
