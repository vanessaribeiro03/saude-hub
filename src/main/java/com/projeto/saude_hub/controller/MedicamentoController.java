package com.projeto.saude_hub.controller;

import com.projeto.saude_hub.controller.dto.MedicamentoDto;
import com.projeto.saude_hub.service.MedicamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @PostMapping
    public ResponseEntity<MedicamentoDto> create(@RequestBody MedicamentoDto dto) {
        MedicamentoDto novoMedicamento = medicamentoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMedicamento);
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoDto>> findAll() {
        List<MedicamentoDto> medicamentos = medicamentoService.findAll();
        return ResponseEntity.ok(medicamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoDto> findById(@PathVariable Long id) {
        Optional<MedicamentoDto> medicamento = medicamentoService.findById(id);
        return medicamento.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoDto> update(@PathVariable Long id, @RequestBody MedicamentoDto dto) {
        Optional<MedicamentoDto> medicamentoAtualizado = medicamentoService.update(id, dto);
        return medicamentoAtualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
