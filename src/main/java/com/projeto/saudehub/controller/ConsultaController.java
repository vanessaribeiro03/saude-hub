package com.projeto.saudehub.controller;

import com.projeto.saudehub.controller.dto.ConsultaDto;
import com.projeto.saudehub.domain.model.consulta.StatusConsulta;
import com.projeto.saudehub.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Consultas", description = "Endpoints relacionados as consultas")
@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    @Autowired
    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @Operation(summary = "Cria uma nova consulta", description = "Cria uma nova consulta e retorna os dados cadastrados.")
    @PostMapping
    public ResponseEntity<ConsultaDto> create(@RequestBody ConsultaDto consultaDto) {
        ConsultaDto novaConsulta = new ConsultaDto(consultaService.create(consultaDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConsulta);
    }

    @Operation(summary = "Busca uma consulta por ID", description = "Retorna os dados de uma consulta específica pelo ID.")
    @ApiResponse(responseCode = "200", description = "Consulta encontrada")
    @ApiResponse(responseCode = "404", description = "Consulta não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDto> findById(@PathVariable Long id) {
        Optional<ConsultaDto> consulta = consultaService.findById(id);
        return consulta.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Busca todas as consultas", description = "Retorna uma lista com todas as consultas cadastradas.")
    @GetMapping
    public ResponseEntity<List<ConsultaDto>> findAll() {
        List<ConsultaDto> consultas = consultaService.findAll();
        return ResponseEntity.ok(consultas);
    }

    @Operation(summary = "Busca consultas por status", description = "Retorna uma lista de consultas com o status fornecido.")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ConsultaDto>> findByStatus(@PathVariable StatusConsulta status) {
        List<ConsultaDto> consultas = consultaService.findByStatus(status);
        return ResponseEntity.ok(consultas);
    }

    @Operation(summary = "Atualiza uma consulta", description = "Atualiza os dados de uma consulta existente com o ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Consulta atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Consulta não encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDto> update(@PathVariable Long id, @RequestBody ConsultaDto consultaDto) {
        Optional<ConsultaDto> consultaAtualizada = consultaService.update(id, consultaDto);
        return consultaAtualizada.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Deleta uma consulta", description = "Remove a consulta com o ID fornecido.")
    @ApiResponse(responseCode = "204", description = "Consulta excluída com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        consultaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}