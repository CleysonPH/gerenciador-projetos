package com.cleysonph.gerenciadorprojetos.core.events;

import com.cleysonph.gerenciadorprojetos.core.models.Tarefa;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class TarefaConcluidaEvent extends ApplicationEvent {

    private final Tarefa tarefa;

    public TarefaConcluidaEvent(Object source, Tarefa tarefa) {
        super(source);
        this.tarefa = tarefa;
    }

}
