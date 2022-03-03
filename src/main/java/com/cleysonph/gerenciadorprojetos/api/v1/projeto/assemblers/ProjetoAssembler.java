package com.cleysonph.gerenciadorprojetos.api.v1.projeto.assemblers;

import java.util.Collection;
import java.util.List;

import com.cleysonph.gerenciadorprojetos.api.v1.projeto.dtos.ProjetoResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.routes.ProjetoRoutes;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.assemblers.Assembler;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ResourceCollectionResponse;
import com.cleysonph.gerenciadorprojetos.core.utils.SecurityUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProjetoAssembler implements Assembler<ProjetoResponse> {

    private final SecurityUtils securityUtils;

    @Override
    public ProjetoResponse toResource(ProjetoResponse resource) {
        var projetoId = resource.getId();

        resource.add(
            ProjetoRoutes.getSelfLink(projetoId),
            ProjetoRoutes.getFindTarefasByProjetoIdLink(projetoId)
        );
        resource.addAllIf(
            securityUtils.isAdmin() || securityUtils.isGerente(),
            List.of(
                ProjetoRoutes.getUpdateByIdLink(projetoId),
                ProjetoRoutes.getDeleteByIdLink(projetoId)
            )
        );
        resource.addIf(
            resource.isCriado() && securityUtils.isUsuario(),
            ProjetoRoutes.getMarkAsAceitoLink(projetoId)
        );

        return resource;
    }

    @Override
    public ResourceCollectionResponse<ProjetoResponse> toResourceCollection(
        Collection<ProjetoResponse> resourceCollection
    ) {
        var resourceCollectionResponse = new ResourceCollectionResponse<>(
            resourceCollection
                .stream()
                .map(this::toResource)
                .toList()
        );
        resourceCollectionResponse.add(ProjetoRoutes.getCreateLink());
        return resourceCollectionResponse;
    }

}
