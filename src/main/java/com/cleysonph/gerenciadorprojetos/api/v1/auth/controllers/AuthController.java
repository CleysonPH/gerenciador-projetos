package com.cleysonph.gerenciadorprojetos.api.v1.auth.controllers;

import com.cleysonph.gerenciadorprojetos.api.v1.auth.dtos.CredentialsRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.auth.dtos.TokenResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.config.ApiSwaggerConfig;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ErrorResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Api(tags = ApiSwaggerConfig.TAG_AUTH)
public interface AuthController {

    @ApiOperation("Realiza a autenticação de um usuário a partir das credenciais")
    @ApiResponse(responseCode = "200", description = "Autenticação realizada com sucesso")
    @ApiResponse(responseCode = "401", description = "Erro ao realizar autenticação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    TokenResponse authenticate(CredentialsRequest credentialsRequest);

}
