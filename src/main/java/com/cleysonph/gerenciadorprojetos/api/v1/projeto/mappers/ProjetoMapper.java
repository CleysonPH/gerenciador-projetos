package com.cleysonph.gerenciadorprojetos.api.v1.projeto.mappers;

import com.cleysonph.gerenciadorprojetos.api.v1.projeto.dtos.ProjetoRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.dtos.ProjetoResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.mappers.ModelToResponseMapper;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.mappers.RequestToModelMapper;
import com.cleysonph.gerenciadorprojetos.core.models.Projeto;

public interface ProjetoMapper extends
    ModelToResponseMapper<Projeto, ProjetoResponse>,
    RequestToModelMapper<ProjetoRequest, Projeto> {

}
