package com.cleysonph.gerenciadorprojetos.api.v1.tarefa.mappers;

import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos.TarefaCreateRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos.TarefaResponse;
import com.cleysonph.gerenciadorprojetos.core.models.Projeto;
import com.cleysonph.gerenciadorprojetos.core.models.Tarefa;
import com.cleysonph.gerenciadorprojetos.core.repositories.ProjetoRepository;

import org.springframework.beans.BeanUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TarefaMapperImpl implements TarefaMapper {

    private final ProjetoRepository projetoRepository;

    @Override
    public TarefaResponse toResponse(Tarefa tarefa) {
        var tarefaResponse = new TarefaResponse();
        BeanUtils.copyProperties(tarefa, tarefaResponse);
        tarefaResponse.setProjetoId(tarefa.getProjeto().getId());
        return tarefaResponse;
    }

    @Override
    public Tarefa toModel(TarefaCreateRequest tarefaRequest) {
        var tarefa = new Tarefa();
        BeanUtils.copyProperties(tarefaRequest, tarefa);
        tarefa.setProjeto(getProjeto(tarefaRequest));
        return tarefa;
    }

    private Projeto getProjeto(TarefaCreateRequest tarefaRequest) {
        return projetoRepository.findByIdOrElseThrow(tarefaRequest.getProjetoId());
    }

}
