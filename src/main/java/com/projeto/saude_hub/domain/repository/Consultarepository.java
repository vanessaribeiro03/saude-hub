package com.projeto.saude_hub.domain.repository;

import com.projeto.saude_hub.domain.model.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Consultarepository extends JpaRepository<Consulta, Long> {
}
