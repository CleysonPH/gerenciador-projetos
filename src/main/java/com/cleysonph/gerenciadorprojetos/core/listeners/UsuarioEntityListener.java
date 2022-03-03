package com.cleysonph.gerenciadorprojetos.core.listeners;

import javax.persistence.PrePersist;

import com.cleysonph.gerenciadorprojetos.core.models.Usuario;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario.Status;

public class UsuarioEntityListener {

    @PrePersist
    public void prePersist(Usuario usuario) {
        usuario.setStatus(Status.ATIVO);
    }

}
