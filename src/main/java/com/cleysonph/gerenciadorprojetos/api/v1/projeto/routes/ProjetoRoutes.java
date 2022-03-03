package com.cleysonph.gerenciadorprojetos.api.v1.projeto.routes;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.http.HttpMethod.*;

import com.cleysonph.gerenciadorprojetos.api.v1.projeto.controllers.ProjetoControllerImpl;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.queryfilters.ProjetoFindAllQueryFilter;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.routes.BaseRoutes;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class ProjetoRoutes {

    private ProjetoRoutes() {}

    public static final String COLLECTION_RESOURCE_PATH = "/projetos";
    public static final String RESOURCE_PATH = "/{projetoId}";
    public static final String BASE_URI = BaseRoutes.BASE_URI + COLLECTION_RESOURCE_PATH;
    public static final String CREATE_URI = BASE_URI;
    public static final String FIND_ALL_URI = BASE_URI;
    public static final String FIND_BY_ID_URI = BASE_URI + RESOURCE_PATH;
    public static final String DELETE_BY_ID_URI = BASE_URI + RESOURCE_PATH;
    public static final String UPDATE_BY_ID_URI = BASE_URI + RESOURCE_PATH;
    public static final String ACEITAR_BY_ID_URI = BASE_URI + RESOURCE_PATH + "/aceitar";
    public static final String FIND_TAREFAS_BY_PROJETO_ID_URI = BASE_URI + RESOURCE_PATH + "/tarefas";

    public static WebMvcLinkBuilder getCreateLinkBuilder() {
        return linkTo(methodOn(ProjetoControllerImpl.class).create(null));
    }

    public static WebMvcLinkBuilder getFindAllLinkBuilder() {
        return getFindAllLinkBuilder(null, null);
    }

    public static WebMvcLinkBuilder getFindAllLinkBuilder(ProjetoFindAllQueryFilter queryFilter) {
        return getFindAllLinkBuilder(queryFilter, null);
    }

    public static WebMvcLinkBuilder getFindAllLinkBuilder(Pageable pageable) {
        return getFindAllLinkBuilder(null, pageable);
    }

    public static WebMvcLinkBuilder getFindAllLinkBuilder(ProjetoFindAllQueryFilter queryFilter, Pageable pageable) {
        return linkTo(methodOn(ProjetoControllerImpl.class).findAll(queryFilter, pageable));
    }

    public static WebMvcLinkBuilder getFindByIdLinkBuilder() {
        return getFindByIdLinkBuilder(null);
    }

    public static WebMvcLinkBuilder getFindByIdLinkBuilder(Long projetoId) {
        return linkTo(methodOn(ProjetoControllerImpl.class).findById(projetoId));
    }

    public static WebMvcLinkBuilder getDeleteByIdLinkBuilder(Long projetoId) {
        return linkTo(methodOn(ProjetoControllerImpl.class).deleteById(projetoId));
    }

    public static WebMvcLinkBuilder getDeleteByIdLinkBuilder() {
        return getDeleteByIdLinkBuilder(null);
    }

    public static WebMvcLinkBuilder getUpdateByIdLinkBuilder(Long projetoId) {
        return linkTo(methodOn(ProjetoControllerImpl.class).updateById(null, projetoId));
    }

    public static WebMvcLinkBuilder getUpdateByIdLinkBuilder() {
        return getUpdateByIdLinkBuilder(null);
    }

    public static WebMvcLinkBuilder getMarkAsAceitoLinkBuilder(Long projetoId) {
        return linkTo(methodOn(ProjetoControllerImpl.class).markAsAceito(projetoId));
    }

    public static WebMvcLinkBuilder getMarkAsAceitoLinkBuilder() {
        return getMarkAsAceitoLinkBuilder(null);
    }

    public static WebMvcLinkBuilder getFindTarefasByProjetoIdLinkBuilder(Long projetoId) {
        return linkTo(methodOn(ProjetoControllerImpl.class).findTarefasByProjetoId(projetoId));
    }

    public static WebMvcLinkBuilder getFindTarefasByProjetoIdLinkBuilder() {
        return getFindTarefasByProjetoIdLinkBuilder(null);
    }

    public static Link getCreateLink() {
        return getCreateLinkBuilder()
            .withRel("criarProjeto")
            .withType(POST.name());
    }

    public static Link getFindAllLink() {
        return getFindAllLinkBuilder()
            .withRel("projetos")
            .withType(GET.name());
    }

    public static Link getSelfLink(Long projetoId) {
        return getFindByIdLinkBuilder(projetoId)
            .withSelfRel()
            .withType(GET.name());
    }

    public static Link getDeleteByIdLink(Long projetoId) {
        return getDeleteByIdLinkBuilder(projetoId)
            .withRel("excluirProjeto")
            .withType(DELETE.name());
    }

    public static Link getUpdateByIdLink(Long projetoId) {
        return getUpdateByIdLinkBuilder(projetoId)
            .withRel("atualizarProjeto")
            .withType(PUT.name());
    }

    public static Link getMarkAsAceitoLink(Long projetoId) {
        return getMarkAsAceitoLinkBuilder(projetoId)
            .withRel("aceitarProjeto")
            .withType(PATCH.name());
    }

    public static Link getFindTarefasByProjetoIdLink(Long projetoId) {
        return getFindTarefasByProjetoIdLinkBuilder(projetoId)
            .withRel("tarefas")
            .withType(GET.name());
    }

}
