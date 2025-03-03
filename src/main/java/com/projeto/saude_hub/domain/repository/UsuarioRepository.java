package com.projeto.saude_hub.domain.repository;

import com.projeto.saude_hub.domain.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
    List<Usuario> findByNomeContainingIgnoreCase(String nome);
}
