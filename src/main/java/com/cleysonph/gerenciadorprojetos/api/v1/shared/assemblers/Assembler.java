package com.cleysonph.gerenciadorprojetos.api.v1.shared.assemblers;

import java.util.Collection;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ResourceCollectionResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ResourceResponse;

public interface Assembler<R extends ResourceResponse> {

    R toResource(R resource);

    ResourceCollectionResponse<R> toResourceCollection(Collection<R> resourceCollection);



}
