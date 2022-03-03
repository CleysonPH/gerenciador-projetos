package com.cleysonph.gerenciadorprojetos.api.v1.shared.filters;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ErrorResponse;
import com.cleysonph.gerenciadorprojetos.core.services.auth.adapters.AuthenticationService;
import com.cleysonph.gerenciadorprojetos.core.services.token.adapters.TokenService;
import com.cleysonph.gerenciadorprojetos.core.services.token.exceptions.TokenServiceException;
import com.cleysonph.gerenciadorprojetos.core.utils.DateTimeUtils;
import com.cleysonph.gerenciadorprojetos.core.utils.SecurityUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccessTokenRequestFilter extends OncePerRequestFilter {

    private static final String TOKEN_TYPE = "Bearer ";

    private final ObjectMapper objectMapper;
    private final TokenService tokenService;
    private final SecurityUtils securityUtils;
    private final AuthenticationService authenticationService;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            tryDoFilterInternal(request, response, filterChain);
        } catch (TokenServiceException exception) {
            handlerTokenServiceException(request, response, exception);
        }
    }

    private void handlerTokenServiceException(
        HttpServletRequest request,
        HttpServletResponse response,
        TokenServiceException exception
    ) throws IOException, JsonProcessingException {
        var status = HttpStatus.UNAUTHORIZED;
        var body = ErrorResponse.builder()
            .error(status.getReasonPhrase())
            .status(status.value())
            .cause(exception.getClass().getSimpleName())
            .timestamp(DateTimeUtils.now())
            .message(exception.getLocalizedMessage())
            .path(request.getRequestURI())
            .build();
        response.setStatus(status.value());
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write(objectMapper.writeValueAsString(body));
    }

    private void tryDoFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws IOException, ServletException {
        var email = "";
        var authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isTokenPresent(authorizationHeader)) {
            var accessToken = authorizationHeader.substring(TOKEN_TYPE.length());
            email = tokenService.getSubjectFromAccessToken(accessToken);
        }
        if (isEmailNotInContext(email)) {
            addEmailInContext(email, request);
        }
        filterChain.doFilter(request, response);
    }

    private void addEmailInContext(String email, HttpServletRequest request) {
        var user = authenticationService.loadUserByUsername(email);
        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        securityUtils.setAuthentication(authentication);
    }

    private boolean isEmailNotInContext(String email) {
        return !email.isEmpty() && securityUtils.getAuthentication() == null;
    }

    private boolean isTokenPresent(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader.startsWith(TOKEN_TYPE);
    }

}
