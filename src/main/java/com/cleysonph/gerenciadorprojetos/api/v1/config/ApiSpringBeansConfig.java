package com.cleysonph.gerenciadorprojetos.api.v1.config;

import com.cleysonph.gerenciadorprojetos.api.v1.auth.services.AuthService;
import com.cleysonph.gerenciadorprojetos.api.v1.auth.services.AuthServiceImpl;
import com.cleysonph.gerenciadorprojetos.api.v1.me.mappers.MeMapper;
import com.cleysonph.gerenciadorprojetos.api.v1.me.mappers.MeMapperImpl;
import com.cleysonph.gerenciadorprojetos.api.v1.me.services.MeService;
import com.cleysonph.gerenciadorprojetos.api.v1.me.services.MeServiceImpl;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.assemblers.ProjetoAssembler;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.assemblers.ProjetoPagedAssembler;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.dtos.ProjetoResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.mappers.ProjetoMapper;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.mappers.ProjetoMapperImpl;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.services.ProjetoService;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.services.ProjetoServiceImpl;
import com.cleysonph.gerenciadorprojetos.api.v1.projeto.validators.ProjetoExistsByIdValidator;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.assemblers.Assembler;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.assemblers.PagedAssembler;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.filters.AccessTokenRequestFilter;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.handlers.ApiAccessDeniedHandler;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.handlers.ApiAuthenticationEntryPoint;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.permissions.ApiPermissions;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.utils.RoutePathVariablesUtils;
import com.cleysonph.gerenciadorprojetos.api.v1.shared.utils.RoutePathVariablesUtilsImpl;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.assemblers.TarefaAssembler;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.dtos.TarefaResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.mappers.TarefaMapper;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.mappers.TarefaMapperImpl;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.services.TarefaService;
import com.cleysonph.gerenciadorprojetos.api.v1.tarefa.services.TarefaServiceImpl;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.assemblers.UsuarioAssembler;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.assemblers.UsuarioPagedAssembler;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.dtos.UsuarioResponse;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.mappers.UsuarioMapper;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.mappers.UsuarioMapperImpl;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.services.UsuarioService;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.services.UsuarioServiceImpl;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.validators.UsuarioExistsByIdValidator;
import com.cleysonph.gerenciadorprojetos.api.v1.usuario.validators.UsuarioNonExistsByEmailValidator;
import com.cleysonph.gerenciadorprojetos.core.repositories.ProjetoRepository;
import com.cleysonph.gerenciadorprojetos.core.repositories.TarefaRepository;
import com.cleysonph.gerenciadorprojetos.core.repositories.UsuarioRepository;
import com.cleysonph.gerenciadorprojetos.core.services.auth.adapters.AuthenticationService;
import com.cleysonph.gerenciadorprojetos.core.services.token.adapters.TokenService;
import com.cleysonph.gerenciadorprojetos.core.utils.SecurityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class ApiSpringBeansConfig {

    @Bean
    public ProjetoMapper projetoMapper(UsuarioRepository usuarioRepository) {
        return new ProjetoMapperImpl(usuarioRepository);
    }

    @Bean
    public UsuarioMapper usuarioMapper() {
        return new UsuarioMapperImpl();
    }

    @Bean
    public UsuarioNonExistsByEmailValidator usuarioNonExistsByEmailValidator(
        UsuarioRepository usuarioRepository,
        RoutePathVariablesUtils routePathVariablesUtils
    ) {
        return new UsuarioNonExistsByEmailValidator(usuarioRepository, routePathVariablesUtils);
    }

    @Bean
    public UsuarioExistsByIdValidator usuarioExistsByIdValidator(
        UsuarioRepository usuarioRepository
    ) {
        return new UsuarioExistsByIdValidator(usuarioRepository);
    }

    @Bean
    public ProjetoService projetoService(
        TarefaMapper tarefaMapper,
        ProjetoMapper projetoMapper,
        SecurityUtils securityUtils,
        TarefaRepository tarefaRepository,
        ProjetoRepository projetoRepository
    ) {
        return new ProjetoServiceImpl(
            tarefaMapper, projetoMapper, securityUtils, tarefaRepository, projetoRepository
        );
    }

    @Bean
    public UsuarioService usuarioService(
        UsuarioMapper usuarioMapper,
        PasswordEncoder passwordEncoder,
        UsuarioRepository usuarioRepository
    ) {
        return new UsuarioServiceImpl(usuarioMapper, passwordEncoder, usuarioRepository);
    }

    @Bean
    public RoutePathVariablesUtils routePathVariablesUtils(ObjectMapper objectMapper) {
        return new RoutePathVariablesUtilsImpl(objectMapper);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(ObjectMapper objectMapper) {
        return new ApiAccessDeniedHandler(objectMapper);
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(ObjectMapper objectMapper) {
        return new ApiAuthenticationEntryPoint(objectMapper);
    }

    @Bean
    public AccessTokenRequestFilter accessTokenRequestFilter(
        ObjectMapper objectMapper,
        TokenService tokenService,
        SecurityUtils securityUtils,
        AuthenticationService authenticationService
    ) {
        return new AccessTokenRequestFilter(objectMapper, tokenService, securityUtils, authenticationService);
    }

    @Bean
    public AuthService authService(
        TokenService tokenService,
        AuthenticationManager authenticationManager
    ) {
        return new AuthServiceImpl(tokenService, authenticationManager);
    }

    @Bean
    public ApiPermissions apiPermissions(
        SecurityUtils securityUtils,
        TarefaRepository tarefaRepository,
        ProjetoRepository projetoRepository
    ) {
        return new ApiPermissions(securityUtils, tarefaRepository, projetoRepository);
    }

    @Bean
    public MeMapper meMapper() {
        return new MeMapperImpl();
    }

    @Bean
    public MeService meService(
        MeMapper meMapper,
        SecurityUtils securityUtils
    ) {
        return new MeServiceImpl(meMapper, securityUtils);
    }

    @Bean
    public ProjetoExistsByIdValidator projetoExistsByIdValidator(
        ProjetoRepository projetoRepository
    ) {
        return new ProjetoExistsByIdValidator(projetoRepository);
    }

    @Bean
    public TarefaMapper tarefaMapper(ProjetoRepository projetoRepository) {
        return new TarefaMapperImpl(projetoRepository);
    }

    @Bean
    public TarefaService tarefaService(
        TarefaMapper tarefaMapper,
        TarefaRepository tarefaRepository,
        ApplicationEventPublisher applicationEventPublisher
    ) {
        return new TarefaServiceImpl(tarefaMapper, tarefaRepository, applicationEventPublisher);
    }

    @Bean
    public Assembler<ProjetoResponse> projetoAssembler(SecurityUtils securityUtils) {
        return new ProjetoAssembler(securityUtils);
    }

    @Bean
    public Assembler<TarefaResponse> tarefaAssembler(SecurityUtils securityUtils) {
        return new TarefaAssembler(securityUtils);
    }

    @Bean
    public Assembler<UsuarioResponse> usuarioAssembler() {
        return new UsuarioAssembler();
    }

    @Bean
    public PagedAssembler<ProjetoResponse> projetoPagedAssembler(
        PagedResourcesAssembler<ProjetoResponse> pagedResourcesAssembler
     ) {
        return new ProjetoPagedAssembler(pagedResourcesAssembler);
    }

    @Bean
    public PagedAssembler<UsuarioResponse> usuarioPagedAssembler(
        PagedResourcesAssembler<UsuarioResponse> pagedResourcesAssembler
    ) {
        return new UsuarioPagedAssembler(pagedResourcesAssembler);
    }

}
