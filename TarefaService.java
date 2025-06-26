package com.todo.service;

import com.todo.model.StatusTarefa;
import com.todo.model.Tarefa;
import com.todo.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    
    @Autowired
    private TarefaRepository tarefaRepository;
    
    // Criar nova tarefa
    public Tarefa criarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }
    
    // Buscar todas as tarefas
    public List<Tarefa> buscarTodasTarefas() {
        return tarefaRepository.findAll();
    }
    
    // Buscar tarefa por ID
    public Optional<Tarefa> buscarTarefaPorId(Long id) {
        return tarefaRepository.findById(id);
    }
    
    // Buscar tarefas por status
    public List<Tarefa> buscarTarefasPorStatus(StatusTarefa status) {
        return tarefaRepository.findByStatus(status);
    }
    
    // Buscar tarefas por usuário
    public List<Tarefa> buscarTarefasPorUsuario(String nomeUsuario) {
        return tarefaRepository.findTarefasPorUsuarioOrdenadas(nomeUsuario);
    }
    
    // Buscar tarefas por usuário e status
    public List<Tarefa> buscarTarefasPorUsuarioEStatus(String nomeUsuario, StatusTarefa status) {
        return tarefaRepository.findByNomeUsuarioAndStatus(nomeUsuario, status);
    }
    
    // Atualizar tarefa
    public Tarefa atualizarTarefa(Long id, Tarefa tarefaAtualizada) {
        Optional<Tarefa> tarefaExistente = tarefaRepository.findById(id);
        if (tarefaExistente.isPresent()) {
            Tarefa tarefa = tarefaExistente.get();
            tarefa.setDescricao(tarefaAtualizada.getDescricao());
            tarefa.setStatus(tarefaAtualizada.getStatus());
            tarefa.setPrioridade(tarefaAtualizada.getPrioridade());
            return tarefaRepository.save(tarefa);
        }
        throw new RuntimeException("Tarefa não encontrada com ID: " + id);
    }
    
    // Marcar tarefa como concluída
    public Tarefa marcarComoConcluida(Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        if (tarefa.isPresent()) {
            Tarefa t = tarefa.get();
            t.setStatus(StatusTarefa.CONCLUIDA);
            return tarefaRepository.save(t);
        }
        throw new RuntimeException("Tarefa não encontrada com ID: " + id);
    }
    
    // Marcar tarefa como urgente
    public Tarefa marcarComoUrgente(Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        if (tarefa.isPresent()) {
            Tarefa t = tarefa.get();
            t.setStatus(StatusTarefa.URGENTE);
            return tarefaRepository.save(t);
        }
        throw new RuntimeException("Tarefa não encontrada com ID: " + id);
    }
    
    // Deletar tarefa
    public void deletarTarefa(Long id) {
        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Tarefa não encontrada com ID: " + id);
        }
    }
    
    // Contar tarefas por status
    public long contarTarefasPorStatus(StatusTarefa status) {
        return tarefaRepository.countByStatus(status);
    }
    
    // Contar tarefas por usuário e status
    public long contarTarefasPorUsuarioEStatus(String nomeUsuario, StatusTarefa status) {
        return tarefaRepository.countByNomeUsuarioAndStatus(nomeUsuario, status);
    }
    
    // Buscar tarefas pendentes ordenadas
    public List<Tarefa> buscarTarefasPendentesOrdenadas() {
        return tarefaRepository.findTarefasPendentesOrdenadas();
    }
} 