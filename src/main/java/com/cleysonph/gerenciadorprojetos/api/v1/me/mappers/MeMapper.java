package com.cleysonph.gerenciadorprojetos.api.v1.me.mappers;

import com.cleysonph.gerenciadorprojetos.api.v1.me.dtos.MeResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.mappers.ModelToResponseMapper;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario;

public interface MeMapper extends ModelToResponseMapper<Usuario, MeResponse> {}
