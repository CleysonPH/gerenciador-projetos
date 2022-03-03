package com.cleysonph.gerenciadorprojetos.api.v1.me.controllers;

import com.cleysonph.gerenciadorprojetos.api.v1.me.dtos.MeResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.me.routes.MeRoutes;
import com.cleysonph.gerenciadorprojetos.api.v1.me.services.MeService;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.assemblers.Assembler;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MeControllerImpl implements MeController {

    private final MeService meService;
    private final Assembler<MeResponse> meAssembler;

    @Override
    @GetMapping(MeRoutes.ME_URI)
    @PreAuthorize("isAuthenticated")
    public MeResponse me() {
        var me = meService.me();
        return meAssembler.toResource(me);
    }

}
