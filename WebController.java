package com.todo.controller;

import com.todo.model.StatusTarefa;
import com.todo.model.Tarefa;
import com.todo.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WebController {
    
    @Autowired
    private TarefaService tarefaService;
    
    // Página principal - To-Do List
    @GetMapping("/")
    public String home(@RequestParam(defaultValue = "Usuario") String nomeUsuario, Model model) {
        List<Tarefa> tarefas = tarefaService.buscarTarefasPorUsuario(nomeUsuario);
        model.addAttribute("tarefas", tarefas);
        model.addAttribute("nomeUsuario", nomeUsuario);
        return "to_do_list";
    }
    
    // Página de detalhes das tarefas
    @GetMapping("/detalhes")
    public String detalhes(@RequestParam(defaultValue = "Usuario") String nomeUsuario, Model model) {
        List<Tarefa> todasTarefas = tarefaService.buscarTarefasPorUsuario(nomeUsuario);
        List<Tarefa> tarefasPendentes = tarefaService.buscarTarefasPorUsuarioEStatus(nomeUsuario, StatusTarefa.PENDENTE);
        List<Tarefa> tarefasConcluidas = tarefaService.buscarTarefasPorUsuarioEStatus(nomeUsuario, StatusTarefa.CONCLUIDA);
        List<Tarefa> tarefasUrgentes = tarefaService.buscarTarefasPorUsuarioEStatus(nomeUsuario, StatusTarefa.URGENTE);
        
        model.addAttribute("todasTarefas", todasTarefas);
        model.addAttribute("tarefasPendentes", tarefasPendentes);
        model.addAttribute("tarefasConcluidas", tarefasConcluidas);
        model.addAttribute("tarefasUrgentes", tarefasUrgentes);
        model.addAttribute("nomeUsuario", nomeUsuario);
        
        return "detalhes";
    }
    
    // Criar nova tarefa via formulário
    @PostMapping("/tarefa/criar")
    public String criarTarefa(@RequestParam String descricao, 
                             @RequestParam(defaultValue = "Usuario") String nomeUsuario,
                             @RequestParam(required = false) StatusTarefa status) {
        Tarefa novaTarefa = new Tarefa(descricao, nomeUsuario);
        if (status != null) {
            novaTarefa.setStatus(status);
        }
        tarefaService.criarTarefa(novaTarefa);
        return "redirect:/?nomeUsuario=" + nomeUsuario;
    }
    
    // Marcar tarefa como concluída
    @PostMapping("/tarefa/{id}/concluir")
    public String marcarConcluida(@PathVariable Long id, 
                                 @RequestParam(defaultValue = "Usuario") String nomeUsuario) {
        tarefaService.marcarComoConcluida(id);
        return "redirect:/?nomeUsuario=" + nomeUsuario;
    }
    
    // Deletar tarefa
    @PostMapping("/tarefa/{id}/deletar")
    public String deletarTarefa(@PathVariable Long id, 
                               @RequestParam(defaultValue = "Usuario") String nomeUsuario) {
        tarefaService.deletarTarefa(id);
        return "redirect:/?nomeUsuario=" + nomeUsuario;
    }
    
    // Filtrar tarefas por status
    @GetMapping("/filtrar/{status}")
    public String filtrarPorStatus(@PathVariable StatusTarefa status,
                                  @RequestParam(defaultValue = "Usuario") String nomeUsuario,
                                  Model model) {
        List<Tarefa> todasTarefas = tarefaService.buscarTarefasPorUsuario(nomeUsuario);
        List<Tarefa> tarefasPendentes = tarefaService.buscarTarefasPorUsuarioEStatus(nomeUsuario, StatusTarefa.PENDENTE);
        List<Tarefa> tarefasConcluidas = tarefaService.buscarTarefasPorUsuarioEStatus(nomeUsuario, StatusTarefa.CONCLUIDA);
        List<Tarefa> tarefasUrgentes = tarefaService.buscarTarefasPorUsuarioEStatus(nomeUsuario, StatusTarefa.URGENTE);

        model.addAttribute("todasTarefas", todasTarefas);
        model.addAttribute("tarefasPendentes", tarefasPendentes);
        model.addAttribute("tarefasConcluidas", tarefasConcluidas);
        model.addAttribute("tarefasUrgentes", tarefasUrgentes);
        model.addAttribute("nomeUsuario", nomeUsuario);
        model.addAttribute("statusFiltro", status);
        return "detalhes";
    }
    
    // Editar descrição da tarefa
    @PostMapping("/tarefa/{id}/editar")
    public String editarDescricaoTarefa(@PathVariable Long id,
                                        @RequestParam String descricao,
                                        @RequestParam(defaultValue = "Usuario") String nomeUsuario) {
        Tarefa tarefa = tarefaService.buscarTarefaPorId(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        tarefa.setDescricao(descricao);
        tarefaService.atualizarTarefa(id, tarefa);
        return "redirect:/?nomeUsuario=" + nomeUsuario;
    }
    
    // Página de detalhes de datas da tarefa
    @GetMapping("/tarefa/{id}/calendar")
    public String detalhesCalendarioTarefa(@PathVariable Long id, Model model) {
        Tarefa tarefa = tarefaService.buscarTarefaPorId(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        model.addAttribute("tarefa", tarefa);
        return "calendar_details";
    }
} 