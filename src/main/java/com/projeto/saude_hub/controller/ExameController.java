package com.projeto.saude_hub.controller;

import com.projeto.saude_hub.controller.dto.ExameDto;
import com.projeto.saude_hub.service.ExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exames")
public class ExameController {

    private final ExameService exameService;

    @Autowired
    public ExameController(ExameService exameService) {
        this.exameService = exameService;
    }

    @PostMapping
    public ResponseEntity<ExameDto> create(@RequestBody ExameDto exameDto) {
        ExameDto exameCriado = new ExameDto(exameService.create(exameDto));
        return new ResponseEntity<>(exameCriado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExameDto> findById(@PathVariable Long id) {
        Optional<ExameDto> exameDto = exameService.findById(id);
        return exameDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ExameDto> findAll() {
        return exameService.findAll();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ExameDto>> buscarExamesPorNome(@PathVariable String nome) {
        List<ExameDto> exames = exameService.findByNome(nome);
        return ResponseEntity.ok(exames);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ExameDto> update(@PathVariable Long id, @RequestBody ExameDto exameDto) {
        Optional<ExameDto> exameAtualizado = exameService.update(id, exameDto);
        return exameAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        exameService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
