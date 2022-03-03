package com.cleysonph.gerenciadorprojetos.api.v1.usuario.services;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.DefaultResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.dtos.CreateUsuarioRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.dtos.UpdateUsuarioRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.dtos.UsuarioResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.queryfilters.UsuarioFindAllQueryFilter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {

    UsuarioResponse create(CreateUsuarioRequest createUsuarioRequest);

    Page<UsuarioResponse> findAll(UsuarioFindAllQueryFilter queryFilter, Pageable pageable);

    UsuarioResponse findById(Long usuarioId);

    void deleteById(Long usuarioId);

    UsuarioResponse updateById(Long usuarioId, UpdateUsuarioRequest updateUsuarioRequest);

    DefaultResponse markAsInativo(Long usuarioId);

    DefaultResponse markAsAtivo(Long usuarioId);

}
