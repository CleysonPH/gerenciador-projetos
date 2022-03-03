package com.cleysonph.gerenciadorprojetos.api.v1.config;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ErrorResponse;
import com.fasterxml.classmate.TypeResolver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
@RequiredArgsConstructor
public class ApiSwaggerConfig {

    private static final String CONTACT_NAME = "Gerenciado de Projetos";
    private static final String CONTACT_URL = "https://www.treinaweb.com.br/contato";
    private static final String CONTACT_EMAIL = "contato@treinaweb.com.br";
    private static final String API_TITLE = "API Back-end Gerenciador de Projetos";
    private static final String API_DESCRIPTION = "API para gerenciamento de projetos dos funcionarios da TreinaWeb";
    private static final String API_VERSION = "1.0.0";
    private static final String BASE_PACKAGE = "com.cleysonph.gerenciadorprojetos.api.v1";

    private final TypeResolver typeResolver;

    public static final String TAG_ME = "Me";
    public static final String TAG_AUTH = "Auth";
    public static final String TAG_TAREFA = "Tarefas";
    public static final String TAG_PROJETO = "Projetos";
    public static final String TAG_USUARIO = "Usuarios";

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
            .apiInfo(buildApiInfo())
            .securityContexts(Arrays.asList(securityContext()))
            .securitySchemes(Arrays.asList(apiKey()))
            .additionalModels(typeResolver.resolve(ErrorResponse.class))
            .produces(Set.of("application/json"))
            .useDefaultResponseMessages(false)
            .select()
            .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
            .paths(PathSelectors.any())
            .build()
            .tags(
                new Tag(TAG_ME, "Me Controller"),
                new Tag(TAG_AUTH, "Auth Controller"),
                new Tag(TAG_TAREFA, "Tarefa Controller"),
                new Tag(TAG_PROJETO, "Projeto Controller"),
                new Tag(TAG_USUARIO, "Usuario Controller")
            );
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
            .title(API_TITLE)
            .description(API_DESCRIPTION)
            .version(API_VERSION)
            .contact(new Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL))
            .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .build();
    }

    private List<SecurityReference> defaultAuth() {
        var authorizationScope = new AuthorizationScope("global", "access");
        var authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

}
