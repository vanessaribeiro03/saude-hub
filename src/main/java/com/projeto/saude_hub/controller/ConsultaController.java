package com.projeto.saude_hub.controller;

import com.projeto.saude_hub.controller.dto.ConsultaDto;
import com.projeto.saude_hub.domain.model.consulta.StatusConsulta;
import com.projeto.saude_hub.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    @Autowired
    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<ConsultaDto> create(@RequestBody ConsultaDto consultaDto) {
        ConsultaDto novaConsulta = new ConsultaDto(consultaService.create(consultaDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConsulta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDto> findById(@PathVariable Long id) {
        Optional<ConsultaDto> consulta = consultaService.findById(id);
        return consulta.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<ConsultaDto>> findAll() {
        List<ConsultaDto> consultas = consultaService.findAll();
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ConsultaDto>> findByStatus(@PathVariable StatusConsulta status) {
        List<ConsultaDto> consultas = consultaService.findByStatus(status);
        return ResponseEntity.ok(consultas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDto> update(@PathVariable Long id, @RequestBody ConsultaDto consultaDto) {
        Optional<ConsultaDto> consultaAtualizada = consultaService.update(id, consultaDto);
        return consultaAtualizada.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        consultaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
