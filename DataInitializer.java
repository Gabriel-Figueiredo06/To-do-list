package com.todo.config;

import com.todo.model.StatusTarefa;
import com.todo.model.Tarefa;
import com.todo.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    public void run(String... args) throws Exception {
        // Verifica se já existem tarefas no banco
        if (tarefaRepository.count() == 0) {
            // Cria tarefas de exemplo
            criarTarefasExemplo();
        }
    }

    private void criarTarefasExemplo() {
        // Tarefas para o usuário "João"
        Tarefa tarefa1 = new Tarefa("Estudar Spring Boot", "João");
        tarefa1.setStatus(StatusTarefa.PENDENTE);
        tarefaRepository.save(tarefa1);

        Tarefa tarefa2 = new Tarefa("Fazer exercícios", "João");
        tarefa2.setStatus(StatusTarefa.CONCLUIDA);
        tarefaRepository.save(tarefa2);

        Tarefa tarefa3 = new Tarefa("Reunião importante", "João");
        tarefa3.setStatus(StatusTarefa.URGENTE);
        tarefaRepository.save(tarefa3);

        // Tarefas para o usuário "Maria"
        Tarefa tarefa4 = new Tarefa("Comprar mantimentos", "Maria");
        tarefa4.setStatus(StatusTarefa.PENDENTE);
        tarefaRepository.save(tarefa4);

        Tarefa tarefa5 = new Tarefa("Ler livro técnico", "Maria");
        tarefa5.setStatus(StatusTarefa.PENDENTE);
        tarefaRepository.save(tarefa5);

        System.out.println("Tarefas de exemplo criadas com sucesso!");
    }
} 