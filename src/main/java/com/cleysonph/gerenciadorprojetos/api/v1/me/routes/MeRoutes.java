package com.cleysonph.gerenciadorprojetos.api.v1.me.routes;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.http.HttpMethod.*;

import com.cleysonph.gerenciadorprojetos.api.v1.me.controllers.MeControllerImpl;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.routes.BaseRoutes;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class MeRoutes {

    private MeRoutes() {}

    public static final String COLLECTION_RESOURCE_PATH = "/me";
    public static final String BASE_URI = BaseRoutes.BASE_URI + COLLECTION_RESOURCE_PATH;
    public static final String ME_URI = BASE_URI;


    public static WebMvcLinkBuilder getMeLinkBuilder() {
        return linkTo(methodOn(MeControllerImpl.class).me());
    }

    public static Link getSelfLink() {
        return getMeLinkBuilder()
            .withSelfRel()
            .withType(GET.name());
    }

}
