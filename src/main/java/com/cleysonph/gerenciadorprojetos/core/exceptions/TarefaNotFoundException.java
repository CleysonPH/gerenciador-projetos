package com.cleysonph.gerenciadorprojetos.core.exceptions;

public class TarefaNotFoundException extends EntityNotFoundException {

    public TarefaNotFoundException() {
        super("Tarefa não encontrada");
    }

    public TarefaNotFoundException(String message) {
        super(message);
    }

}
