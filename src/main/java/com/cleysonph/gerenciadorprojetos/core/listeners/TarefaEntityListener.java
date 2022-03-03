package com.cleysonph.gerenciadorprojetos.core.listeners;

import javax.persistence.PrePersist;

import com.cleysonph.gerenciadorprojetos.core.models.Tarefa;
import com.cleysonph.gerenciadorprojetos.core.models.Tarefa.Status;

public class TarefaEntityListener {

    @PrePersist
    public void prePersist(Tarefa tarefa) {
        tarefa.setStatus(Status.CRIADA);
    }

}
