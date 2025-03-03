package com.projeto.saude_hub.domain.repository;

import com.projeto.saude_hub.domain.model.exame.Exame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExameRepository extends JpaRepository<Exame, Long> {
    List<Exame> findByNomeContainingIgnoreCase(String nome);
}
