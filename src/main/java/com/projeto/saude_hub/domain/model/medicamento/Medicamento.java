package com.projeto.saude_hub.domain.model.medicamento;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_medicamentos")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String dosagem;

    @Column(nullable = false)
    private String periodo;

    @Column(nullable = true)
    private String viaAdministracao;

    @Column(nullable = true)
    private String instrucoesUso;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime atualizadoEm;


    public Medicamento() {}

    public Medicamento(Long id, String nome, String dosagem, String periodo, String viaAdministracao, String instrucoesUso,  LocalDateTime criadoEm,
                       LocalDateTime atualizadoEm) {
        this.id = id;
        this.nome = nome;
        this.dosagem = dosagem;
        this.periodo = periodo;
        this.viaAdministracao = viaAdministracao;
        this.instrucoesUso = instrucoesUso;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getViaAdministracao() {
        return viaAdministracao;
    }

    public void setViaAdministracao(String viaAdministracao) {
        this.viaAdministracao = viaAdministracao;
    }

    public String getInstrucoesUso() {
        return instrucoesUso;
    }

    public void setInstrucoesUso(String instrucoesUso) {
        this.instrucoesUso = instrucoesUso;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }
}
