package com.cleysonph.gerenciadorprojetos.api.v1.projeto.controllers;

import com.cleysonph.gerenciadorprojetos.api.v1.config.ApiSwaggerConfig;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.dtos.ProjetoRequest;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.dtos.ProjetoResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.queryfilters.ProjetoFindAllQueryFilter;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.annotations.PageableParams;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.DefaultResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ErrorResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ResourceCollectionResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos.TarefaResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = ApiSwaggerConfig.TAG_PROJETO)
public interface ProjetoController {

    @ApiOperation("Cadastrar novo projeto")
    @ApiResponse(responseCode = "201", description = "Projeto criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Houveram erros de validação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "401", description = "Usuário não está autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "403", description = "Usuário autenticado não tem acesso para realizar essa operação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    ProjetoResponse create(ProjetoRequest projetoRequest);

    @PageableParams
    @ApiOperation("Listar projetos cadastrados")
    @ApiResponse(responseCode = "200", description = "Listagem realizada com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não está autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    ResourceCollectionResponse<ProjetoResponse> findAll(ProjetoFindAllQueryFilter queryFilter, @ApiIgnore Pageable pageable);

    @ApiOperation("Buscar um projeto por id")
    @ApiResponse(responseCode = "200", description = "Projeto encontrado com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não está autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "403", description = "Usuário autenticado não tem acesso para realizar essa operação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Projeto não encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    ProjetoResponse findById(Long projetoId);

    @ApiOperation("Excluir um projeto por id")
    @ApiResponse(responseCode = "204", description = "Projeto excluido com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não está autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "403", description = "Usuário autenticado não tem acesso para realizar essa operação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Projeto não encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    ResponseEntity<Void> deleteById(Long projetoId);

    @ApiOperation("Atualizar projeto por id")
    @ApiResponse(responseCode = "200", description = "Projeto atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Houveram erros de validação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "401", description = "Usuário não está autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "403", description = "Usuário autenticado não tem acesso para realizar essa operação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Projeto não encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    ProjetoResponse updateById(ProjetoRequest projetoRequest, Long projetoId);

    @ApiOperation("Marcar um projeto com aceito")
    @ApiResponse(responseCode = "200", description = "Projeto marcado com aceito com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não está autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "403", description = "Usuário autenticado não tem acesso para realizar essa operação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Projeto não encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    DefaultResponse markAsAceito(Long projetoId);

    @ApiOperation("Listar tarefas de um projeto")
    @ApiResponse(responseCode = "200", description = "Listagem realizada com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não está autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "403", description = "Usuário autenticado não tem acesso para realizar essa operação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Projeto não encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    ResourceCollectionResponse<TarefaResponse> findTarefasByProjetoId(Long projetoId);

}
