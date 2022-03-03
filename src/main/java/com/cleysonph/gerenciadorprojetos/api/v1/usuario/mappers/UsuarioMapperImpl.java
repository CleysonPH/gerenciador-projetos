package com.cleysonph.gerenciadorprojetos.api.v1.usuario.mappers;

import com.cleysonph.gerenciadorprojetos.api.v1.usuario.dtos.CreateUsuarioRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.dtos.UsuarioResponse;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario;

import org.springframework.beans.BeanUtils;

public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioResponse toResponse(Usuario usuario) {
        var usuarioResponse = new UsuarioResponse();
        BeanUtils.copyProperties(usuario, usuarioResponse);
        return usuarioResponse;
    }

    @Override
    public Usuario toModel(CreateUsuarioRequest createUsuarioRequest) {
        var usuario = new Usuario();
        BeanUtils.copyProperties(createUsuarioRequest, usuario, "password");
        return usuario;
    }

}
