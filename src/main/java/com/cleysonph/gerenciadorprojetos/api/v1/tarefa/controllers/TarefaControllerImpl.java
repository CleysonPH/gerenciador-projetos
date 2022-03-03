package com.cleysonph.gerenciadorprojetos.api.v1.tarefa.controllers;

import javax.validation.Valid;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.assemblers.Assembler;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.DefaultResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos.TarefaCreateRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos.TarefaResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos.TarefaUpdateRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.routes.TarefaRoutes;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.services.TarefaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TarefaControllerImpl implements TarefaController {

    private final TarefaService tarefaService;
    private final Assembler<TarefaResponse> tarefaAssembler;

    @Override
    @PreAuthorize("""
        hasAuthority('ADMIN')
        or
        @apiPermissions.isLiderOfProjeto(#tarefaCreateRequest.projetoId)
    """)
    @PostMapping(TarefaRoutes.CREATE_URI)
    @ResponseStatus(code = HttpStatus.CREATED)
    public TarefaResponse create(@RequestBody @Valid TarefaCreateRequest tarefaCreateRequest) {
        var tarefa = tarefaService.create(tarefaCreateRequest);
        return tarefaAssembler.toResource(tarefa);
    }

    @Override
    @PreAuthorize("""
        hasAuthority('ADMIN')
        or
        @apiPermissions.isLiderOfTarefa(#tarefaId)
        or
        @apiPermissions.isResponsavelOfTarefa(#tarefaId)
    """)
    @GetMapping(TarefaRoutes.FIND_BY_ID_URI)
    public TarefaResponse findById(@PathVariable Long tarefaId) {
        var tarefa = tarefaService.findById(tarefaId);
        return tarefaAssembler.toResource(tarefa);
    }

    @Override
    @PreAuthorize("""
        hasAuthority('ADMIN')
        or
        @apiPermissions.isLiderOfTarefa(#tarefaId)
    """)
    @PutMapping(TarefaRoutes.UPDATE_BY_ID_URI)
    public TarefaResponse updateById(
        @PathVariable Long tarefaId,
        @RequestBody @Valid TarefaUpdateRequest tarefaUpdateRequest
    ) {
        var tarefa = tarefaService.updateById(tarefaId, tarefaUpdateRequest);
        return tarefaAssembler.toResource(tarefa);
    }

    @Override
    @PreAuthorize("""
        hasAuthority('ADMIN')
        or
        @apiPermissions.isLiderOfTarefa(#tarefaId)
    """)
    @DeleteMapping(TarefaRoutes.DELETE_BY_ID_URI)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteById(@PathVariable Long tarefaId) {
        tarefaService.deleteById(tarefaId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PreAuthorize("""
        isAuthenticated
        and
        @apiPermissions.isResponsavelOfTarefa(#tarefaId)
    """)
    @PatchMapping(TarefaRoutes.MARK_AS_CONCLUIDA_BY_ID_URI)
    public DefaultResponse markAsConcluida(@PathVariable Long tarefaId) {
        return tarefaService.markAsConcluida(tarefaId);
    }

}
