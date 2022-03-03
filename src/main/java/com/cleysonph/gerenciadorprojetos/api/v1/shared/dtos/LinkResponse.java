package com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos;

import org.springframework.hateoas.Link;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkResponse {

    private String rel;
    private String href;
    private String type;

    public static LinkResponse of(Link link) {
        return LinkResponse.builder()
            .rel(link.getRel().value())
            .href(link.getHref())
            .type(link.getType() == null ? "GET" : link.getType())
            .build();
    }

}
