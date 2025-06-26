package com.todo.controller;

import com.todo.model.StatusTarefa;
import com.todo.model.Tarefa;
import com.todo.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {
    
    @Autowired
    private TarefaService tarefaService;
    
    // Criar nova tarefa
    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@Valid @RequestBody Tarefa tarefa) {
        Tarefa novaTarefa = tarefaService.criarTarefa(tarefa);
        return new ResponseEntity<>(novaTarefa, HttpStatus.CREATED);
    }
    
    // Buscar todas as tarefas
    @GetMapping
    public ResponseEntity<List<Tarefa>> buscarTodasTarefas() {
        List<Tarefa> tarefas = tarefaService.buscarTodasTarefas();
        return ResponseEntity.ok(tarefas);
    }
    
    // Buscar tarefa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarTarefaPorId(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaService.buscarTarefaPorId(id);
        return tarefa.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Buscar tarefas por status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Tarefa>> buscarTarefasPorStatus(@PathVariable StatusTarefa status) {
        List<Tarefa> tarefas = tarefaService.buscarTarefasPorStatus(status);
        return ResponseEntity.ok(tarefas);
    }
    
    // Buscar tarefas por usuário
    @GetMapping("/usuario/{nomeUsuario}")
    public ResponseEntity<List<Tarefa>> buscarTarefasPorUsuario(@PathVariable String nomeUsuario) {
        List<Tarefa> tarefas = tarefaService.buscarTarefasPorUsuario(nomeUsuario);
        return ResponseEntity.ok(tarefas);
    }
    
    // Buscar tarefas por usuário e status
    @GetMapping("/usuario/{nomeUsuario}/status/{status}")
    public ResponseEntity<List<Tarefa>> buscarTarefasPorUsuarioEStatus(
            @PathVariable String nomeUsuario, 
            @PathVariable StatusTarefa status) {
        List<Tarefa> tarefas = tarefaService.buscarTarefasPorUsuarioEStatus(nomeUsuario, status);
        return ResponseEntity.ok(tarefas);
    }
    
    // Atualizar tarefa
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @Valid @RequestBody Tarefa tarefa) {
        try {
            Tarefa tarefaAtualizada = tarefaService.atualizarTarefa(id, tarefa);
            return ResponseEntity.ok(tarefaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Marcar tarefa como concluída
    @PatchMapping("/{id}/concluir")
    public ResponseEntity<Tarefa> marcarComoConcluida(@PathVariable Long id) {
        try {
            Tarefa tarefa = tarefaService.marcarComoConcluida(id);
            return ResponseEntity.ok(tarefa);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Marcar tarefa como urgente
    @PatchMapping("/{id}/urgente")
    public ResponseEntity<Tarefa> marcarComoUrgente(@PathVariable Long id) {
        try {
            Tarefa tarefa = tarefaService.marcarComoUrgente(id);
            return ResponseEntity.ok(tarefa);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Deletar tarefa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        try {
            tarefaService.deletarTarefa(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Contar tarefas por status
    @GetMapping("/contar/status/{status}")
    public ResponseEntity<Long> contarTarefasPorStatus(@PathVariable StatusTarefa status) {
        long count = tarefaService.contarTarefasPorStatus(status);
        return ResponseEntity.ok(count);
    }
    
    // Contar tarefas por usuário e status
    @GetMapping("/contar/usuario/{nomeUsuario}/status/{status}")
    public ResponseEntity<Long> contarTarefasPorUsuarioEStatus(
            @PathVariable String nomeUsuario, 
            @PathVariable StatusTarefa status) {
        long count = tarefaService.contarTarefasPorUsuarioEStatus(nomeUsuario, status);
        return ResponseEntity.ok(count);
    }
    
    // Buscar tarefas pendentes ordenadas
    @GetMapping("/pendentes")
    public ResponseEntity<List<Tarefa>> buscarTarefasPendentesOrdenadas() {
        List<Tarefa> tarefas = tarefaService.buscarTarefasPendentesOrdenadas();
        return ResponseEntity.ok(tarefas);
    }
} 