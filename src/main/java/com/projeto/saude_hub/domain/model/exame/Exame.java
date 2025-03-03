package com.projeto.saude_hub.domain.model.exame;

import com.projeto.saude_hub.domain.model.usuario.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_exames")
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "data_exame", nullable = false)
    private LocalDateTime dataExame;

    @Column(nullable = false)
    private String local;

    @Column
    private String resultado;

    @Column
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Exame() {}

    public Exame(Long id, String nome, LocalDateTime dataExame, String local, String resultado, String observacoes, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.dataExame = dataExame;
        this.local = local;
        this.resultado = null;
        this.observacoes = observacoes;
        this.usuario = usuario;
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

    public LocalDateTime getDataExame() {
        return dataExame;
    }

    public void setDataExame(LocalDateTime dataExame) {
        this.dataExame = dataExame;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
