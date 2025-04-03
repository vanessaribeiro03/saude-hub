package com.projeto.saudehub.controller;

import com.projeto.saudehub.controller.dto.MedicamentoDto;
import com.projeto.saudehub.service.MedicamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Medicamentos", description = "Endpoints relacionados aos medicamentos")
@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @Operation(summary = "Cria um novo medicamento", description = "Cria um medicamento e retorna os dados cadastrados.")
    @PostMapping
    public ResponseEntity<MedicamentoDto> create(@RequestBody MedicamentoDto dto) {
        MedicamentoDto novoMedicamento = medicamentoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMedicamento);
    }

    @Operation(summary = "Busca todos os medicamentos", description = "Retorna uma lista com todos os medicamentos cadastrados.")
    @GetMapping
    public ResponseEntity<List<MedicamentoDto>> findAll() {
        List<MedicamentoDto> medicamentos = medicamentoService.findAll();
        return ResponseEntity.ok(medicamentos);
    }

    @Operation(summary = "Busca um medicamento por ID", description = "Retorna os dados de um medicamento específico pelo ID.")
    @ApiResponse(responseCode = "200", description = "Medicamento encontrado")
    @ApiResponse(responseCode = "404", description = "Medicamento não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoDto> findById(@PathVariable Long id) {
        Optional<MedicamentoDto> medicamento = medicamentoService.findById(id);
        return medicamento.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Busca medicamentos por nome", description = "Retorna uma lista de medicamentos com o nome fornecido.")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<MedicamentoDto>> buscarMedicamentosPorNome(@PathVariable String nome) {
        List<MedicamentoDto> medicamentos = medicamentoService.findByNome(nome);
        return ResponseEntity.ok(medicamentos);
    }

    @Operation(summary = "Atualiza um medicamento", description = "Atualiza os dados de um medicamento existente com o ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Medicamento atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Medicamento não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoDto> update(@PathVariable Long id, @RequestBody MedicamentoDto dto) {
        Optional<MedicamentoDto> medicamentoAtualizado = medicamentoService.update(id, dto);
        return medicamentoAtualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Deleta um medicamento", description = "Remove o medicamento com o ID fornecido.")
    @ApiResponse(responseCode = "204", description = "Medicamento excluído com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}