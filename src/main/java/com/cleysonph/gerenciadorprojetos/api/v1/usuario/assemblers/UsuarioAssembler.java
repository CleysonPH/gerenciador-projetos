package com.cleysonph.gerenciadorprojetos.api.v1.usuario.assemblers;

import java.util.Collection;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.assemblers.Assembler;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ResourceCollectionResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.dtos.UsuarioResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.routes.UsuarioRoutes;

public class UsuarioAssembler implements Assembler<UsuarioResponse> {

    @Override
    public UsuarioResponse toResource(UsuarioResponse resource) {
        var usuarioId = resource.getId();

        resource.add(
            UsuarioRoutes.getSelfLink(usuarioId),
            UsuarioRoutes.getUpdateByIdLink(usuarioId),
            UsuarioRoutes.getDeleteByIdLink(usuarioId)
        );
        resource.addIf(
            resource.isAtivo(),
            UsuarioRoutes.getMarkAsInativoLink(usuarioId)
        );
        resource.addIf(
            resource.isInativo(),
            UsuarioRoutes.getMarkAsInativoLink(usuarioId)
        );

        return resource;
    }

    @Override
    public ResourceCollectionResponse<UsuarioResponse> toResourceCollection(
        Collection<UsuarioResponse> resourceCollection
    ) {
        var resourceCollectionResponse = new ResourceCollectionResponse<>(
            resourceCollection
                .stream()
                .map(this::toResource)
                .toList()
        );
        resourceCollectionResponse.add(UsuarioRoutes.getCreateLink());
        return resourceCollectionResponse;
    }

}
