package com.cleysonph.gerenciadorprojetos.api.v1.me.controllers;

import com.cleysonph.gerenciadorprojetos.api.v1.config.ApiSwaggerConfig;
import com.cleysonph.gerenciadorprojetos.api.v1.me.dtos.MeResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ErrorResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Api(tags = ApiSwaggerConfig.TAG_ME)
public interface MeController {

    @ApiOperation("Detalha os dados do usuário logado")
    @ApiResponse(responseCode = "200", description = "Detalhes do usuário logado exibidos com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não está autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    MeResponse me();

}
