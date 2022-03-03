package com.cleysonph.gerenciadorprojetos.api.v1.tarefa.mappers;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.mappers.ModelToResponseMapper;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.mappers.RequestToModelMapper;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos.TarefaCreateRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos.TarefaResponse;
import com.cleysonph.gerenciadorprojetos.core.models.Tarefa;

public interface TarefaMapper extends
    ModelToResponseMapper<Tarefa, TarefaResponse>,
    RequestToModelMapper<TarefaCreateRequest, Tarefa> {

}
