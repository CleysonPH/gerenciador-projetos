package com.cleysonph.gerenciadorprojetos.api.v1.me.services;

import com.cleysonph.gerenciadorprojetos.api.v1.me.dtos.MeResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.me.mappers.MeMapper;
import com.cleysonph.gerenciadorprojetos.core.utils.SecurityUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MeServiceImpl implements MeService {

    private final MeMapper meMapper;
    private final SecurityUtils securityUtils;

    @Override
    public MeResponse me() {
        var usuario = securityUtils.getAuthenticatedUser().getUsuario();
        return meMapper.toResponse(usuario);
    }

}
