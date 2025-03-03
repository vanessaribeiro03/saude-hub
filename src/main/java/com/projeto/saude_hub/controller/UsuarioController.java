package com.projeto.saude_hub.controller;

import com.projeto.saude_hub.controller.dto.UsuarioDto;
import com.projeto.saude_hub.domain.model.usuario.Usuario;
import com.projeto.saude_hub.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDto> createuser(@RequestBody UsuarioDto usuarioDto){
        Usuario usuarioCriado = usuarioService.create(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioDto(usuarioCriado));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> findAll(){
        List<Usuario> usuarios = usuarioService.findAll();

        List<UsuarioDto> usuarioDto = usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(usuarioDto);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<UsuarioDto>> buscarUsuariosPorNome(@PathVariable String nome) {
        List<UsuarioDto> usuarios = usuarioService.findByNome(nome);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> findById(@PathVariable Long id) {
        return usuarioService.findBydId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> update(@PathVariable Long id, @RequestBody UsuarioDto usuarioDto) {
        return usuarioService.update(id, usuarioDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
