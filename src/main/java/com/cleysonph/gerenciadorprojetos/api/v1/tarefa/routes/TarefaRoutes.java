package com.cleysonph.gerenciadorprojetos.api.v1.tarefa.routes;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.http.HttpMethod.*;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.routes.BaseRoutes;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.controllers.TarefaControllerImpl;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class TarefaRoutes {

    private TarefaRoutes() {}

    public static final String COLLECTION_RESOURCE_PATH = "/tarefas";
    public static final String RESOURCE_PATH = "/{tarefaId}";
    public static final String BASE_URI = BaseRoutes.BASE_URI + COLLECTION_RESOURCE_PATH;
    public static final String CREATE_URI = BASE_URI;
    public static final String FIND_BY_ID_URI = BASE_URI + RESOURCE_PATH;
    public static final String DELETE_BY_ID_URI = BASE_URI + RESOURCE_PATH;
    public static final String UPDATE_BY_ID_URI = BASE_URI + RESOURCE_PATH;
    public static final String MARK_AS_CONCLUIDA_BY_ID_URI = BASE_URI + RESOURCE_PATH + "/concluir";

    public static WebMvcLinkBuilder getCreateLinkBuilder() {
        return linkTo(methodOn(TarefaControllerImpl.class).create(null));
    }

    public static WebMvcLinkBuilder getFindByIdLinkBuilder(Long tarefaId) {
        return linkTo(methodOn(TarefaControllerImpl.class).findById(tarefaId));
    }

    public static WebMvcLinkBuilder getFindByIdLinkBuilder() {
        return getFindByIdLinkBuilder(null);
    }

    public static WebMvcLinkBuilder getUpdateByIdLinkBuilder(Long tarefaId) {
        return linkTo(methodOn(TarefaControllerImpl.class).updateById(tarefaId, null));
    }

    public static WebMvcLinkBuilder getUpdateByIdLinkBuilder() {
        return getUpdateByIdLinkBuilder(null);
    }

    public static WebMvcLinkBuilder getDeleteByIdLinkBuilder(Long tarefaId) {
        return linkTo(methodOn(TarefaControllerImpl.class).deleteById(tarefaId));
    }

    public static WebMvcLinkBuilder getDeleteByIdLinkBuilder() {
        return getDeleteByIdLinkBuilder(null);
    }

    public static WebMvcLinkBuilder getMarkAsConcluidaLinkBuilder(Long tarefaId) {
        return linkTo(methodOn(TarefaControllerImpl.class).markAsConcluida(tarefaId));
    }

    public static WebMvcLinkBuilder getMarkAsConcluidaLinkBuilder() {
        return getMarkAsConcluidaLinkBuilder(null);
    }

    public static Link getCreateLink() {
        return getCreateLinkBuilder()
            .withRel("criarTarefa")
            .withType(POST.name());
    }

    public static Link getSelfLink(Long tarefaId) {
        return getFindByIdLinkBuilder(tarefaId)
            .withSelfRel()
            .withType(GET.name());
    }

    public static Link getDeleteByIdLink(Long tarefaId) {
        return getDeleteByIdLinkBuilder(tarefaId)
            .withRel("excluirTarefa")
            .withType(DELETE.name());
    }

    public static Link getUpdateByIdLink(Long tarefaId) {
        return getUpdateByIdLinkBuilder(tarefaId)
            .withRel("atualizarTarefa")
            .withType(PUT.name());
    }

    public static Link getMarkAsConcluidaLink(Long tarefaId) {
        return getMarkAsConcluidaLinkBuilder(tarefaId)
            .withRel("concluirTarefa")
            .withType(PATCH.name());
    }


}
