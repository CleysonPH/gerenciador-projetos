package com.cleysonph.gerenciadorprojetos.core.listeners;

import javax.persistence.PrePersist;

import com.cleysonph.gerenciadorprojetos.core.models.Projeto;
import com.cleysonph.gerenciadorprojetos.core.models.Projeto.Status;

public class ProjetoEntityListener {

    @PrePersist
    public void prePersist(Projeto projeto) {
        projeto.setStatus(Status.CRIADO);
    }

}
