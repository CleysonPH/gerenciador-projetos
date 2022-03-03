package com.cleysonph.gerenciadorprojetos.api.v1.shared.mappers;

public interface ModelToResponseMapper<M, R> {

    R toResponse(M model);

}
