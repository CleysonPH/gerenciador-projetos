package com.cleysonph.gerenciadorprojetos.api.v1.me.mappers;

import com.cleysonph.gerenciadorprojetos.api.v1.me.dtos.MeResponse;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario;

import org.springframework.beans.BeanUtils;

public class MeMapperImpl implements MeMapper {

    @Override
    public MeResponse toResponse(Usuario model) {
        var meResponse = new MeResponse();
        BeanUtils.copyProperties(model, meResponse, "password");
        return meResponse;
    }

}
