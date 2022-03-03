package com.cleysonph.gerenciadorprojetos.api.v1.projeto.assemblers;

import com.cleysonph.gerenciadorprojetos.api.v1.projeto.dtos.ProjetoResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.assemblers.Assembler;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.assemblers.PagedAssembler;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ResourceCollectionResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProjetoPagedAssembler implements PagedAssembler<ProjetoResponse> {

    private final PagedResourcesAssembler<ProjetoResponse> pagedResourcesAssembler;

    @Override
    public ResourceCollectionResponse<ProjetoResponse> toResourceCollection(
        Page<ProjetoResponse> resourcePage,
        Assembler<ProjetoResponse> resourceAssembler
    ) {
        var resourceCollectionResponse = resourceAssembler.toResourceCollection(resourcePage.getContent());
        resourceCollectionResponse.addAll(
            pagedResourcesAssembler.toModel(resourcePage).getLinks()
        );
        return resourceCollectionResponse;
    }

}
