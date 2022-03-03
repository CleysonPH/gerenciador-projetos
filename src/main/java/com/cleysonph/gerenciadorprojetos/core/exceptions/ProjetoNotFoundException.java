package com.cleysonph.gerenciadorprojetos.core.exceptions;

public class ProjetoNotFoundException extends EntityNotFoundException {

    public ProjetoNotFoundException() {
        super("Projeto não encontrado");
    }

    public ProjetoNotFoundException(String message) {
        super(message);
    }

}
