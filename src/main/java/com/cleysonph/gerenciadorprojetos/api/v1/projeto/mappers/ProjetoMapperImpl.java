package com.cleysonph.gerenciadorprojetos.api.v1.projeto.mappers;

import com.cleysonph.gerenciadorprojetos.api.v1.projeto.dtos.ProjetoRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.dtos.ProjetoResponse;
import com.cleysonph.gerenciadorprojetos.core.models.Projeto;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario;
import com.cleysonph.gerenciadorprojetos.core.repositories.UsuarioRepository;

import org.springframework.beans.BeanUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProjetoMapperImpl implements ProjetoMapper {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Projeto toModel(ProjetoRequest projetoRequest) {
        var projeto = new Projeto();
        BeanUtils.copyProperties(projetoRequest, projeto, "responsavel");
        projeto.setResponsavel(getResponsavel(projetoRequest));
        return projeto;
    }

    private Usuario getResponsavel(ProjetoRequest projetoRequest) {
        return usuarioRepository.findByIdOrElseThrow(projetoRequest.getResponsavel());
    }

    @Override
    public ProjetoResponse toResponse(Projeto projeto) {
        var projetoResponse = new ProjetoResponse();
        BeanUtils.copyProperties(projeto, projetoResponse, "responsavel");
        projetoResponse.setResponsavelNome(projeto.getResponsavel().getNome());
        projetoResponse.setLiderNome(projeto.getLider().getNome());
        return projetoResponse;
    }

}
