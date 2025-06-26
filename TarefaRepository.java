package com.todo.repository;

import com.todo.model.StatusTarefa;
import com.todo.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    
    // Buscar tarefas por status
    List<Tarefa> findByStatus(StatusTarefa status);
    
    // Buscar tarefas por nome do usuário
    List<Tarefa> findByNomeUsuario(String nomeUsuario);
    
    // Buscar tarefas por nome do usuário e status
    List<Tarefa> findByNomeUsuarioAndStatus(String nomeUsuario, StatusTarefa status);
    
    // Buscar tarefas pendentes ordenadas por data de criação
    @Query("SELECT t FROM Tarefa t WHERE t.status = 'PENDENTE' ORDER BY t.dataCriacao ASC")
    List<Tarefa> findTarefasPendentesOrdenadas();
    
    // Buscar tarefas por nome do usuário ordenadas por data de criação
    @Query("SELECT t FROM Tarefa t WHERE t.nomeUsuario = :nomeUsuario ORDER BY t.dataCriacao DESC")
    List<Tarefa> findTarefasPorUsuarioOrdenadas(@Param("nomeUsuario") String nomeUsuario);
    
    // Contar tarefas por status
    long countByStatus(StatusTarefa status);
    
    // Contar tarefas por usuário e status
    long countByNomeUsuarioAndStatus(String nomeUsuario, StatusTarefa status);
} 