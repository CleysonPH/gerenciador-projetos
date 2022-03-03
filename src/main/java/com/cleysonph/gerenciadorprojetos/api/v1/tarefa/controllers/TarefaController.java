package com.cleysonph.gerenciadorprojetos.api.v1.tarefa.controllers;

import com.cleysonph.gerenciadorprojetos.api.v1.config.ApiSwaggerConfig;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.DefaultResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ErrorResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos.TarefaCreateRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos.TarefaResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos.TarefaUpdateRequest;

import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Api(tags = ApiSwaggerConfig.TAG_TAREFA)
public interface TarefaController {

    @ApiOperation("Cadastrar nova tarefa")
    @ApiResponse(responseCode = "201", description = "Tarefa cadastrada com sucesso")
    @ApiResponse(responseCode = "400", description = "Houveram erros de validação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "401", description = "Usuário não está autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "403", description = "Usuário autenticado não tem acesso para realizar essa operação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    TarefaResponse create(TarefaCreateRequest tarefaCreateRequest);

    @ApiOperation("Buscar tarefa por id")
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não está autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "403", description = "Usuário autenticado não tem acesso para realizar essa operação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    TarefaResponse findById(Long tarefaId);

    @ApiOperation("Atualizar tarefa por id")
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não está autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "403", description = "Usuário autenticado não tem acesso para realizar essa operação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    TarefaResponse updateById(Long tarefaId, TarefaUpdateRequest tarefaUpdateRequest);

    @ApiOperation("Excluir tarefa por id")
    @ApiResponse(responseCode = "204", description = "Tarefa excluida com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não está autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "403", description = "Usuário autenticado não tem acesso para realizar essa operação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    ResponseEntity<Void> deleteById(Long tarefaId);

    @ApiOperation("Marcar tarefa como concluida")
    @ApiResponse(responseCode = "200", description = "Tarefa marcada como concluida com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não está autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "403", description = "Usuário autenticado não tem acesso para realizar essa operação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    DefaultResponse markAsConcluida(Long tarefaId);

}
