package com.cleysonph.gerenciadorprojetos.api.v1.usuario.assemblers;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.assemblers.Assembler;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.assemblers.PagedAssembler;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ResourceCollectionResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.dtos.UsuarioResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioPagedAssembler implements PagedAssembler<UsuarioResponse> {

    private final PagedResourcesAssembler<UsuarioResponse> pagedResourcesAssembler;

    @Override
    public ResourceCollectionResponse<UsuarioResponse> toResourceCollection(
        Page<UsuarioResponse> resourcePage,
        Assembler<UsuarioResponse> resourceAssembler
    ) {
        var resourceCollectionResponse = resourceAssembler.toResourceCollection(resourcePage.getContent());
        resourceCollectionResponse.addAll(
            pagedResourcesAssembler.toModel(resourcePage).getLinks()
        );
        return resourceCollectionResponse;
    }

}
