package com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;

import lombok.Getter;

@Getter
public class ResourceCollectionResponse<R extends ResourceResponse> {

    private List<R> content;

    @JsonProperty("_links")
    private List<LinkResponse> links;

    public ResourceCollectionResponse(List<R> content) {
        this.content = content;
        this.links = new ArrayList<>();
    }

    public void add(Link link) {
        this.links.add(LinkResponse.of(link));
    }

    public void add(Link ...links) {
        for (Link link : links) {
            this.links.add(LinkResponse.of(link));
        }
    }

    public void addIf(boolean guard, Link link) {
        if (guard) {
            this.links.add(LinkResponse.of(link));
        }
    }

    public void addAllIf(boolean guard, Collection<Link> links) {
        if (guard) {
            links.forEach(this::add);
        }
    }

    public void addAll(Links links) {
        links.forEach(this::add);
    }

}
