package com.cleysonph.gerenciadorprojetos.api.v1.usuario.mappers;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.mappers.ModelToResponseMapper;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.mappers.RequestToModelMapper;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.dtos.CreateUsuarioRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.dtos.UsuarioResponse;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario;

public interface UsuarioMapper extends
    ModelToResponseMapper<Usuario, UsuarioResponse>,
    RequestToModelMapper<CreateUsuarioRequest, Usuario> {

}
