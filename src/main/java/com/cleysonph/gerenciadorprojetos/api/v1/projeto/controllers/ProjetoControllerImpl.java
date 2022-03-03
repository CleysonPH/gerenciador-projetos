package com.cleysonph.gerenciadorprojetos.api.v1.projeto.controllers;

import javax.validation.Valid;

import com.cleysonph.gerenciadorprojetos.api.v1.projeto.dtos.ProjetoRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.dtos.ProjetoResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.queryfilters.ProjetoFindAllQueryFilter;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.routes.ProjetoRoutes;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.services.ProjetoService;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.assemblers.Assembler;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.assemblers.PagedAssembler;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.DefaultResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ResourceCollectionResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos.TarefaResponse;

import org.springframework.data.domain.Pageable;
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
public class ProjetoControllerImpl implements ProjetoController {

    private final ProjetoService projetoService;
    private final Assembler<TarefaResponse> tarefaAssembler;
    private final Assembler<ProjetoResponse> projetoAssembler;
    private final PagedAssembler<ProjetoResponse> projetoPagedAssembler;

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN', 'GERENTE')")
    @PostMapping(ProjetoRoutes.CREATE_URI)
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProjetoResponse create(@RequestBody @Valid ProjetoRequest projetoRequest) {
        var projeto = projetoService.create(projetoRequest);
        return projetoAssembler.toResource(projeto);
    }

    @Override
    @PreAuthorize("isAuthenticated")
    @GetMapping(ProjetoRoutes.FIND_ALL_URI)
    public ResourceCollectionResponse<ProjetoResponse> findAll(ProjetoFindAllQueryFilter queryFilter, Pageable pageable) {
        var projetos = projetoService.findAll(queryFilter, pageable);
        return projetoPagedAssembler.toResourceCollection(projetos, projetoAssembler);
    }

    @Override
    @PreAuthorize("""
        hasAuthority('ADMIN')
        or
        @apiPermissions.isLiderOfProjeto(#projetoId)
        or
        @apiPermissions.isResponsavelOfProjeto(#projetoId)
    """)
    @GetMapping(ProjetoRoutes.FIND_BY_ID_URI)
    public ProjetoResponse findById(@PathVariable Long projetoId) {
        var projeto = projetoService.findById(projetoId);
        return projetoAssembler.toResource(projeto);
    }

    @Override
    @PreAuthorize("""
        hasAuthority('ADMIN')
        or
        @apiPermissions.isLiderOfProjeto(#projetoId)
    """)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(ProjetoRoutes.DELETE_BY_ID_URI)
    public ResponseEntity<Void> deleteById(@PathVariable Long projetoId) {
        projetoService.deleteById(projetoId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PreAuthorize("""
        hasAuthority('ADMIN')
        or
        @apiPermissions.isLiderOfProjeto(#projetoId)
    """)
    @PutMapping(ProjetoRoutes.UPDATE_BY_ID_URI)
    public ProjetoResponse updateById(
        @RequestBody @Valid ProjetoRequest projetoRequest,
        @PathVariable Long projetoId
    ) {
        var projeto = projetoService.updateById(projetoRequest, projetoId);
        return projetoAssembler.toResource(projeto);
    }

    @Override
    @PreAuthorize("""
        isAuthenticated
        and
        @apiPermissions.isResponsavelOfProjeto(#projetoId)
    """)
    @PatchMapping(ProjetoRoutes.ACEITAR_BY_ID_URI)
    public DefaultResponse markAsAceito(@PathVariable Long projetoId) {
        return projetoService.markAsAceito(projetoId);
    }

    @Override
    @PreAuthorize("""
        hasAuthority('ADMIN')
        or
        @apiPermissions.isLiderOfProjeto(#projetoId)
        or
        @apiPermissions.isResponsavelOfProjeto(#projetoId)
    """)
    @GetMapping(ProjetoRoutes.FIND_TAREFAS_BY_PROJETO_ID_URI)
    public ResourceCollectionResponse<TarefaResponse> findTarefasByProjetoId(
        @PathVariable Long projetoId
    ) {
        var tarefas = projetoService.findTarefasByProjetoId(projetoId);
        return tarefaAssembler.toResourceCollection(tarefas);
    }

}
