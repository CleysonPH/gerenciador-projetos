package com.cleysonph.gerenciadorprojetos.api.v1.tarefa.services;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.DefaultResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos.TarefaCreateRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos.TarefaResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos.TarefaUpdateRequest;

public interface TarefaService {

    TarefaResponse create(TarefaCreateRequest tarefaCreateRequest);

    TarefaResponse findById(Long tarefaId);

    TarefaResponse updateById(Long tarefaId, TarefaUpdateRequest tarefaUpdateRequest);

    void deleteById(Long tarefaId);

    DefaultResponse markAsConcluida(Long tarefaId);

}
