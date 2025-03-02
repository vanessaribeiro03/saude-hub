package com.projeto.saude_hub.domain.repository;

import com.projeto.saude_hub.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
