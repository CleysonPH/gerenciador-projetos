package com.cleysonph.gerenciadorprojetos.api.v1.tarefa.services;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.DefaultResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos.TarefaCreateRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos.TarefaResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos.TarefaUpdateRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.mappers.TarefaMapper;
import com.cleysonph.gerenciadorprojetos.core.events.TarefaConcluidaEvent;
import com.cleysonph.gerenciadorprojetos.core.models.Tarefa.Status;
import com.cleysonph.gerenciadorprojetos.core.repositories.TarefaRepository;

import org.springframework.context.ApplicationEventPublisher;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TarefaServiceImpl implements TarefaService {

    private final TarefaMapper tarefaMapper;
    private final TarefaRepository tarefaRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public TarefaResponse create(TarefaCreateRequest tarefaCreateRequest) {
        var tarefaToCreate = tarefaMapper.toModel(tarefaCreateRequest);
        var createdTarefa = tarefaRepository.save(tarefaToCreate);
        return tarefaMapper.toResponse(createdTarefa);
    }

    @Override
    public TarefaResponse findById(Long tarefaId) {
        var foundTarefa = tarefaRepository.findByIdOrElseThrow(tarefaId);
        return tarefaMapper.toResponse(foundTarefa);
    }

    @Override
    public TarefaResponse updateById(Long tarefaId, TarefaUpdateRequest tarefaUpdateRequest) {
        var tarefaToUpdate = tarefaRepository.findByIdOrElseThrow(tarefaId);
        tarefaToUpdate.setNome(tarefaUpdateRequest.getNome());
        var updatedTarefa = tarefaRepository.save(tarefaToUpdate);
        return tarefaMapper.toResponse(updatedTarefa);
    }

    @Override
    public void deleteById(Long tarefaId) {
        var tarefaToDelete = tarefaRepository.findByIdOrElseThrow(tarefaId);
        tarefaRepository.delete(tarefaToDelete);
    }

    @Override
    public DefaultResponse markAsConcluida(Long tarefaId) {
        var foundTarefa = tarefaRepository.findByIdOrElseThrow(tarefaId);
        if (!foundTarefa.isConcluida()) {
            foundTarefa.setStatus(Status.CONCLUIDA);
            var tarefaConcluida = tarefaRepository.save(foundTarefa);
            var tarefaConcluidaEvent = new TarefaConcluidaEvent(this, tarefaConcluida);
            applicationEventPublisher.publishEvent(tarefaConcluidaEvent);
        }
        return DefaultResponse.builder()
            .message("Tarefa marcada com concluída com sucesso!")
            .build();
    }

}
