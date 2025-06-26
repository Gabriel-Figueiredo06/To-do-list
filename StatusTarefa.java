package com.todo.model;

public enum StatusTarefa {
    PENDENTE("Pendente"),
    CONCLUIDA("Concluída"),
    URGENTE("Urgente");
    
    private final String descricao;
    
    StatusTarefa(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public String toString() {
        return descricao;
    }
} 