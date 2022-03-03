package com.cleysonph.gerenciadorprojetos.core.services.token.providers;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JjwtConfigProperties {

    private String accessKey;
    private int accessExpiration;

}
