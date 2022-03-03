package com.cleysonph.gerenciadorprojetos.api.v1.shared.mappers;

public interface RequestToModelMapper<R, M> {

    M toModel(R request);

}
