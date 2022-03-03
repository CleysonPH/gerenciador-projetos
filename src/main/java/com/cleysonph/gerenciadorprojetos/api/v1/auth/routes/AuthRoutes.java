package com.cleysonph.gerenciadorprojetos.api.v1.auth.routes;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.http.HttpMethod.*;

import com.cleysonph.gerenciadorprojetos.api.v1.auth.controllers.AuthControllerImpl;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.routes.BaseRoutes;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class AuthRoutes {

    private AuthRoutes() {}

    public static final String COLLECTION_RESOURCE_PATH = "/auth";
    public static final String BASE_URI = BaseRoutes.BASE_URI + COLLECTION_RESOURCE_PATH;
    public static final String AUTHENTICATE_URI = BASE_URI;

    public static WebMvcLinkBuilder getAuthenticateLinkBuilder() {
        return linkTo(methodOn(AuthControllerImpl.class).authenticate(null));
    }

    public static Link getAuthenticateLink() {
        return getAuthenticateLinkBuilder()
            .withRel("auth")
            .withType(POST.name());
    }

}
