package com.projeto.saudehub.controller;

import com.projeto.saudehub.controller.dto.ExameDto;
import com.projeto.saudehub.service.ExameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Exames", description = "Endpoints relacionados aos exames")
@RestController
@RequestMapping("/exames")
public class ExameController {

    private final ExameService exameService;

    @Autowired
    public ExameController(ExameService exameService) {
        this.exameService = exameService;
    }

    @Operation(summary = "Cria um novo exame", description = "Cria um exame e retorna os dados cadastrados.")
    @PostMapping
    public ResponseEntity<ExameDto> create(@RequestBody ExameDto exameDto) {
        ExameDto exameCriado = new ExameDto(exameService.create(exameDto));
        return new ResponseEntity<>(exameCriado, HttpStatus.CREATED);
    }

    @Operation(summary = "Busca um exame por ID", description = "Retorna os dados de um exame específico pelo ID.")
    @ApiResponse(responseCode = "200", description = "Exame encontrado")
    @ApiResponse(responseCode = "404", description = "Exame não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<ExameDto> findById(@PathVariable Long id) {
        Optional<ExameDto> exameDto = exameService.findById(id);
        return exameDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Busca todos os exames", description = "Retorna uma lista com todos os exames cadastrados.")
    @GetMapping
    public List<ExameDto> findAll() {
        return exameService.findAll();
    }

    @Operation(summary = "Busca exames por nome", description = "Retorna uma lista de exames com o nome fornecido.")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ExameDto>> buscarExamesPorNome(@PathVariable String nome) {
        List<ExameDto> exames = exameService.findByNome(nome);
        return ResponseEntity.ok(exames);
    }

    @Operation(summary = "Atualiza um exame", description = "Atualiza os dados de um exame existente com o ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Exame atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Exame não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<ExameDto> update(@PathVariable Long id, @RequestBody ExameDto exameDto) {
        Optional<ExameDto> exameAtualizado = exameService.update(id, exameDto);
        return exameAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deleta um exame", description = "Remove o exame com o ID fornecido.")
    @ApiResponse(responseCode = "204", description = "Exame excluído com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        exameService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


//package com.projeto.saude_hub.controller;
//
//import com.projeto.saude_hub.controller.dto.ExameDto;
//import com.projeto.saude_hub.service.ExameService;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@Tag(name = "Exames", description = "Endpoints relacionados aos exames")
//@RestController
//@RequestMapping("/exames")
//public class ExameController {
//
//    private final ExameService exameService;
//
//    @Autowired
//    public ExameController(ExameService exameService) {
//        this.exameService = exameService;
//    }
//
//    @PostMapping
//    public ResponseEntity<ExameDto> create(@RequestBody ExameDto exameDto) {
//        ExameDto exameCriado = new ExameDto(exameService.create(exameDto));
//        return new ResponseEntity<>(exameCriado, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ExameDto> findById(@PathVariable Long id) {
//        Optional<ExameDto> exameDto = exameService.findById(id);
//        return exameDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @GetMapping
//    public List<ExameDto> findAll() {
//        return exameService.findAll();
//    }
//
//    @GetMapping("/nome/{nome}")
//    public ResponseEntity<List<ExameDto>> buscarExamesPorNome(@PathVariable String nome) {
//        List<ExameDto> exames = exameService.findByNome(nome);
//        return ResponseEntity.ok(exames);
//    }
//
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ExameDto> update(@PathVariable Long id, @RequestBody ExameDto exameDto) {
//        Optional<ExameDto> exameAtualizado = exameService.update(id, exameDto);
//        return exameAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        exameService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//}
