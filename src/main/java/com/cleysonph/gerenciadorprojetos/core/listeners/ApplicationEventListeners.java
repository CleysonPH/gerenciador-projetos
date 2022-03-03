package com.cleysonph.gerenciadorprojetos.core.listeners;

import com.cleysonph.gerenciadorprojetos.core.events.TarefaConcluidaEvent;
import com.cleysonph.gerenciadorprojetos.core.models.Projeto;
import com.cleysonph.gerenciadorprojetos.core.models.Tarefa;
import com.cleysonph.gerenciadorprojetos.core.repositories.ProjetoRepository;
import com.cleysonph.gerenciadorprojetos.core.repositories.TarefaRepository;
import com.cleysonph.gerenciadorprojetos.core.utils.DateTimeUtils;

import org.springframework.context.event.EventListener;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApplicationEventListeners {

    private final TarefaRepository tarefaRepository;
    private final ProjetoRepository projetoRepository;

    @EventListener
    public void handleTarefaConcluidaEvent(TarefaConcluidaEvent event) {
        var tarefa = event.getTarefa();
        var projeto = tarefa.getProjeto();
        if (projeto.isAceito() && !tarefaRepository.existsByProjetoAndStatus(projeto, Tarefa.Status.CRIADA)) {
            var today = DateTimeUtils.today();
            var projetoStatus = projeto.getDataFim().isAfter(today)
                ? Projeto.Status.CONCLUIDO
                : Projeto.Status.CONCLUIDO_COM_ATRASO;
            projeto.setDataEntrega(today);
            projeto.setStatus(projetoStatus);
            projetoRepository.save(projeto);
        }
    }

}
