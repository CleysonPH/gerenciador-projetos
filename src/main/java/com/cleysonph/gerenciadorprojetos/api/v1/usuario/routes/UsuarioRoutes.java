package com.cleysonph.gerenciadorprojetos.api.v1.usuario.routes;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.http.HttpMethod.*;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.routes.BaseRoutes;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.controllers.UsuarioControllerImpl;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.queryfilters.UsuarioFindAllQueryFilter;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class UsuarioRoutes {

    private UsuarioRoutes() {}

    public static final String COLLECTION_RESOURCE_PATH = "/usuarios";
    public static final String RESOURCE_PATH = "/{usuarioId}";
    public static final String BASE_URI = BaseRoutes.BASE_URI + COLLECTION_RESOURCE_PATH;
    public static final String CREATE_URI = BASE_URI;
    public static final String FIND_ALL_URI = BASE_URI;
    public static final String FIND_BY_ID_URI = BASE_URI + RESOURCE_PATH;
    public static final String DELETE_BY_ID_URI = BASE_URI + RESOURCE_PATH;
    public static final String UPDATE_BY_ID_URI = BASE_URI + RESOURCE_PATH;
    public static final String INATIVAR_BY_ID_URI = BASE_URI + RESOURCE_PATH + "/inativar";
    public static final String ATIVAR_BY_ID_URI = BASE_URI + RESOURCE_PATH + "/ativar";

    public static WebMvcLinkBuilder getCreateLinkBuilder() {
        return linkTo(methodOn(UsuarioControllerImpl.class).create(null));
    }

    public static WebMvcLinkBuilder getFindAllLinkBuilder() {
        return getFindAllLinkBuilder(null, null);
    }

    public static WebMvcLinkBuilder getFindAllLinkBuilder(UsuarioFindAllQueryFilter queryFilter) {
        return getFindAllLinkBuilder(queryFilter, null);
    }

    public static WebMvcLinkBuilder getFindAllLinkBuilder(Pageable pageable) {
        return getFindAllLinkBuilder(null, pageable);
    }

    public static WebMvcLinkBuilder getFindAllLinkBuilder(UsuarioFindAllQueryFilter queryFilter, Pageable pageable) {
        return linkTo(methodOn(UsuarioControllerImpl.class).findAll(queryFilter, pageable));
    }

    public static WebMvcLinkBuilder getFindByIdLinkBuilder(Long usuarioId) {
        return linkTo(methodOn(UsuarioControllerImpl.class).findById(usuarioId));
    }

    public static WebMvcLinkBuilder getFindByIdLinkBuilder() {
        return getFindByIdLinkBuilder(null);
    }

    public static WebMvcLinkBuilder getUpdateByIdLinkBuilder(Long usuarioId) {
        return linkTo(methodOn(UsuarioControllerImpl.class).updateById(usuarioId, null));
    }

    public static WebMvcLinkBuilder getUpdateByIdLinkBuilder() {
        return getUpdateByIdLinkBuilder(null);
    }

    public static WebMvcLinkBuilder getDeleteByIdLinkBuilder(Long usuarioId) {
        return linkTo(methodOn(UsuarioControllerImpl.class).deleteById(usuarioId));
    }

    public static WebMvcLinkBuilder getDeleteByIdLinkBuilder() {
        return getDeleteByIdLinkBuilder(null);
    }

    public static WebMvcLinkBuilder getMarkAsInativoLinkBuilder(Long usuarioId) {
        return linkTo(methodOn(UsuarioControllerImpl.class).markAsInativo(usuarioId));
    }

    public static WebMvcLinkBuilder getMarkAsInativoLinkBuilder() {
        return getMarkAsInativoLinkBuilder(null);
    }
    public static WebMvcLinkBuilder getMarkAsAtivoLinkBuilder(Long usuarioId) {
        return linkTo(methodOn(UsuarioControllerImpl.class).markAsAtivo(usuarioId));
    }

    public static WebMvcLinkBuilder getMarkAsAtivoLinkBuilder() {
        return getMarkAsAtivoLinkBuilder(null);
    }

    public static Link getCreateLink() {
        return getCreateLinkBuilder()
            .withRel("criarUsuario")
            .withType(POST.name());
    }

    public static Link getFindAllLink() {
        return getFindAllLinkBuilder()
            .withRel("usuarios")
            .withType(GET.name());
    }

    public static Link getSelfLink(Long usuarioId) {
        return getFindByIdLinkBuilder(usuarioId)
            .withSelfRel()
            .withType(GET.name());
    }

    public static Link getDeleteByIdLink(Long usuarioId) {
        return getDeleteByIdLinkBuilder(usuarioId)
            .withRel("excluirUsuario")
            .withType(DELETE.name());
    }

    public static Link getUpdateByIdLink(Long usuarioId) {
        return getUpdateByIdLinkBuilder(usuarioId)
            .withRel("atualizarUsuario")
            .withType(PUT.name());
    }

    public static Link getMarkAsInativoLink(Long usuarioId) {
        return getMarkAsInativoLinkBuilder(usuarioId)
            .withRel("inativarUsuario")
            .withType(PATCH.name());
    }
    public static Link getMarkAsAtivoLink(Long usuarioId) {
        return getMarkAsAtivoLinkBuilder(usuarioId)
            .withRel("ativarUsuario")
            .withType(PATCH.name());
    }

}
