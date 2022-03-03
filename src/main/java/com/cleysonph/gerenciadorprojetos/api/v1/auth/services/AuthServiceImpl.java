package com.cleysonph.gerenciadorprojetos.api.v1.auth.services;

import com.cleysonph.gerenciadorprojetos.api.v1.auth.dtos.CredentialsRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.auth.dtos.TokenResponse;
import com.cleysonph.gerenciadorprojetos.core.services.token.adapters.TokenService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Override
    public TokenResponse authenticate(CredentialsRequest credentialsRequest) {
        var email = credentialsRequest.getEmail();
        var password = credentialsRequest.getPassword();
        var authentication = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(authentication);
        var access = tokenService.generateAccessToken(email);
        var expiration = tokenService.getExpirationFromAccessToken(access);
        return TokenResponse.builder()
            .access(access)
            .expiresAt(expiration)
            .tokenType("Bearer")
            .build();
    }

}
