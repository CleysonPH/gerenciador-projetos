package com.cleysonph.gerenciadorprojetos.api.v1.shared.assemblers;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ResourceCollectionResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ResourceResponse;

import org.springframework.data.domain.Page;

public interface PagedAssembler<R extends ResourceResponse> {

    ResourceCollectionResponse<R> toResourceCollection(Page<R> resourcePage, Assembler<R> resourceAssembler);

}
