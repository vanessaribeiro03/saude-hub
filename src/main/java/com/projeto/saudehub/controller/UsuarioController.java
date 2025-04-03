package com.projeto.saudehub.controller;

import com.projeto.saudehub.controller.dto.UsuarioDto;
import com.projeto.saudehub.domain.model.usuario.Usuario;
import com.projeto.saudehub.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Usuarios", description = "Endpoints relacionados aos usuários")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Cria um novo usuário", description = "Cria um usuário e retorna os dados cadastrados.")
    @PostMapping
    public ResponseEntity<UsuarioDto> createuser(@RequestBody UsuarioDto usuarioDto){
        Usuario usuarioCriado = usuarioService.create(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioDto(usuarioCriado));
    }

    @Operation(summary = "Busca todos os usuários", description = "Retorna uma lista com todos os usuários cadastrados.")
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> findAll(){
        List<Usuario> usuarios = usuarioService.findAll();
        List<UsuarioDto> usuarioDto = usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(usuarioDto);
    }

    @Operation(summary = "Busca usuários por nome", description = "Retorna uma lista de usuários com o nome fornecido.")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<UsuarioDto>> buscarUsuariosPorNome(@PathVariable String nome) {
        List<UsuarioDto> usuarios = usuarioService.findByNome(nome);
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Busca um usuário por ID", description = "Retorna os dados de um usuário específico pelo ID.")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> findById(@PathVariable Long id) {
        return usuarioService.findBydId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualiza um usuário", description = "Atualiza os dados de um usuário existente com o ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> update(@PathVariable Long id, @RequestBody UsuarioDto usuarioDto) {
        return usuarioService.update(id, usuarioDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deleta um usuário", description = "Remove o usuário com o ID fornecido.")
    @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}