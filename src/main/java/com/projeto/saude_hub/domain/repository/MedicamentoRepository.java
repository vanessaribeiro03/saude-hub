package com.projeto.saude_hub.domain.repository;

import com.projeto.saude_hub.domain.model.medicamento.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    List<Medicamento> findByNomeContainingIgnoreCase(String nome);
}
