package com.cleysonph.gerenciadorprojetos.core.exceptions;

public class UsuarioNotFoundException extends EntityNotFoundException {

    public UsuarioNotFoundException() {
        super("Usuário não encontrado");
    }

    public UsuarioNotFoundException(String message) {
        super(message);
    }

}
