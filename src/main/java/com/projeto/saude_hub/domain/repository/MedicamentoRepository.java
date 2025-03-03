package com.projeto.saude_hub.domain.repository;

import com.projeto.saude_hub.domain.model.medicamento.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
}
