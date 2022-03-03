package com.cleysonph.gerenciadorprojetos.api.v1.auth.controllers;

import javax.validation.Valid;

import com.cleysonph.gerenciadorprojetos.api.v1.auth.dtos.CredentialsRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.auth.dtos.TokenResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.auth.routes.AuthRoutes;
import com.cleysonph.gerenciadorprojetos.api.v1.auth.services.AuthService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    @PostMapping(AuthRoutes.AUTHENTICATE_URI)
    public TokenResponse authenticate(@RequestBody @Valid CredentialsRequest credentialsRequest) {
        return authService.authenticate(credentialsRequest);
    }

}
