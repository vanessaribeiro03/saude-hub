package com.projeto.saudehub.domain.repository;

import com.projeto.saudehub.domain.model.consulta.Consulta;
import com.projeto.saudehub.domain.model.consulta.StatusConsulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByStatus(StatusConsulta status);
}
