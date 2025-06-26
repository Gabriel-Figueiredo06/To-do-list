package com.todo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "tarefas")
public class Tarefa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    
    @NotBlank(message = "A descrição da tarefa é obrigatória")
    @Column(nullable = false)
    private String descricao;
    
    @NotNull(message = "O status da tarefa é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTarefa status;
    
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    
    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;
    
    @Column(name = "nome_usuario")
    private String nomeUsuario;
    
    @Column(name = "prioridade")
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;
    
    public Tarefa() {
        this.dataCriacao = LocalDateTime.now();
        this.status = StatusTarefa.PENDENTE;
    }
    
    public Tarefa(String descricao, String nomeUsuario) {
        this();
        this.descricao = descricao;
        this.nomeUsuario = nomeUsuario;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public StatusTarefa getStatus() {
        return status;
    }
    
    public void setStatus(StatusTarefa status) {
        this.status = status;
        if (status == StatusTarefa.CONCLUIDA && this.dataConclusao == null) {
            this.dataConclusao = LocalDateTime.now();
        }
    }
    
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    
    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }
    
    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
    }
    
    public String getNomeUsuario() {
        return nomeUsuario;
    }
    
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
    public Prioridade getPrioridade() {
        return prioridade;
    }
    
    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }
    
    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                ", dataCriacao=" + dataCriacao +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                '}';
    }
} 