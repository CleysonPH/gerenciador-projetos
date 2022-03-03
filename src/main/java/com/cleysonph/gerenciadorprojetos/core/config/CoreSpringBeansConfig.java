package com.cleysonph.gerenciadorprojetos.core.config;

import com.cleysonph.gerenciadorprojetos.core.listeners.ApplicationEventListeners;
import com.cleysonph.gerenciadorprojetos.core.repositories.ProjetoRepository;
import com.cleysonph.gerenciadorprojetos.core.repositories.TarefaRepository;
import com.cleysonph.gerenciadorprojetos.core.repositories.UsuarioRepository;
import com.cleysonph.gerenciadorprojetos.core.services.auth.adapters.AuthenticationService;
import com.cleysonph.gerenciadorprojetos.core.services.auth.providers.AuthenticarionServiceImpl;
import com.cleysonph.gerenciadorprojetos.core.services.token.adapters.TokenService;
import com.cleysonph.gerenciadorprojetos.core.services.token.providers.JjwtService;
import com.cleysonph.gerenciadorprojetos.core.utils.SecurityUtils;
import com.cleysonph.gerenciadorprojetos.core.services.token.providers.JjwtConfigProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreSpringBeansConfig {

    @Bean
    public AuthenticationService authenticationService(UsuarioRepository usuarioRepository) {
        return new AuthenticarionServiceImpl(usuarioRepository);
    }

    @Bean
    @ConfigurationProperties(prefix = "com.cleysonph.gerenciadorprojetos.token")
    public JjwtConfigProperties jjwtConfigProperties() {
        return new JjwtConfigProperties();
    }

    @Bean
    public TokenService tokenService(JjwtConfigProperties configProperties) {
        return new JjwtService(configProperties);
    }

    @Bean
    public SecurityUtils securityUtils() {
        return new SecurityUtils();
    }

    @Bean
    public ApplicationEventListeners applicationEventListeners(
        TarefaRepository tarefaRepository,
        ProjetoRepository projetoRepository
    ) {
        return new ApplicationEventListeners(tarefaRepository, projetoRepository);
    }

}
