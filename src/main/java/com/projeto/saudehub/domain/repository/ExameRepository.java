package com.projeto.saudehub.domain.repository;

import com.projeto.saudehub.domain.model.exame.Exame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExameRepository extends JpaRepository<Exame, Long> {
    List<Exame> findByNomeContainingIgnoreCase(String nome);
}
