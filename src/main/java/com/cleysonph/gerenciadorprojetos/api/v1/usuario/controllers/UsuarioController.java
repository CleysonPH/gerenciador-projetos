package com.cleysonph.gerenciadorprojetos.api.v1.usuario.controllers;

import com.cleysonph.gerenciadorprojetos.api.v1.config.ApiSwaggerConfig;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.annotations.PageableParams;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.DefaultResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ErrorResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ResourceCollectionResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.dtos.CreateUsuarioRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.dtos.UpdateUsuarioRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.dtos.UsuarioResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.queryfilters.UsuarioFindAllQueryFilter;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = ApiSwaggerConfig.TAG_USUARIO)
public interface UsuarioController {

    @ApiOperation("Cadastar novo usuário")
    @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Houveram erros de validação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "401", description = "Usuário não está autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "403", description = "Usuário autenticado não tem acesso para realizar essa operação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    UsuarioResponse create(CreateUsuarioRequest createUsuarioRequest);

    @PageableParams
    @ApiOperation("Listar usuários cadastrados")
    @ApiResponse(responseCode = "200", description = "Listagem realizada com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não está autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "403", description = "Usuário autenticado não tem acesso para realizar essa operação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    ResourceCollectionResponse<UsuarioResponse> findAll(UsuarioFindAllQueryFilter queryFilter, @ApiIgnore Pageable pageable);

    @ApiOperation("Buscar usuário por id")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não está autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "403", description = "Usuário autenticado não tem acesso para realizar essa operação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    UsuarioResponse findById(Long usuarioId);

    @ApiOperation("Excluir usuário por id")
    @ApiResponse(responseCode = "204", description = "Usuário excluido com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não está autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "403", description = "Usuário autenticado não tem acesso para realizar essa operação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    ResponseEntity<Void> deleteById(Long usuarioId);

    @ApiOperation("Atualizar usuário por id")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Houveram erros de validação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "401", description = "Usuário não está autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "403", description = "Usuário autenticado não tem acesso para realizar essa operação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    UsuarioResponse updateById(Long usuarioId, UpdateUsuarioRequest updateUsuarioRequest);

    @ApiOperation("Marcar usuário como inativo")
    @ApiResponse(responseCode = "200", description = "Usuário marcado com inativo com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não está autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "403", description = "Usuário autenticado não tem acesso para realizar essa operação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    DefaultResponse markAsInativo(Long usuarioId);

    @ApiOperation("Marcar usuário como ativo")
    @ApiResponse(responseCode = "200", description = "Usuário marcado com ativo com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não está autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "403", description = "Usuário autenticado não tem acesso para realizar essa operação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    DefaultResponse markAsAtivo(Long usuarioId);

}
