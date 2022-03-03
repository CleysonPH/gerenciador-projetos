package com.cleysonph.gerenciadorprojetos.api.v1.tarefa.assemblers;

import java.util.Collection;
import java.util.List;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.assemblers.Assembler;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ResourceCollectionResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos.TarefaResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.routes.TarefaRoutes;
import com.cleysonph.gerenciadorprojetos.core.utils.SecurityUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TarefaAssembler implements Assembler<TarefaResponse> {

    private final SecurityUtils securityUtils;

    @Override
    public TarefaResponse toResource(TarefaResponse resource) {
        var tarefaId = resource.getId();

        resource.add(TarefaRoutes.getSelfLink(tarefaId));
        resource.addAllIf(
            securityUtils.isAdmin() || securityUtils.isGerente(),
            List.of(
                TarefaRoutes.getUpdateByIdLink(tarefaId),
                TarefaRoutes.getDeleteByIdLink(tarefaId)
            )
        );
        resource.addIf(
            resource.isCriada() && securityUtils.isUsuario(),
            TarefaRoutes.getMarkAsConcluidaLink(tarefaId)
        );

        return resource;
    }

    @Override
    public ResourceCollectionResponse<TarefaResponse> toResourceCollection(
        Collection<TarefaResponse> resourceCollection
    ) {
        var resourceCollectionResponse = new ResourceCollectionResponse<>(
            resourceCollection
                .stream()
                .map(this::toResource)
                .toList()
        );
        resourceCollectionResponse.add(TarefaRoutes.getCreateLink());
        return resourceCollectionResponse;
    }

}
