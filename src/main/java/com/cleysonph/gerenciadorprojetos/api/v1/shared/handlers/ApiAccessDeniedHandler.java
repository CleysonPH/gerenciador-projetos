package com.cleysonph.gerenciadorprojetos.api.v1.shared.handlers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ErrorResponse;
import com.cleysonph.gerenciadorprojetos.core.utils.DateTimeUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApiAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(
        HttpServletRequest request,
        HttpServletResponse response,
        AccessDeniedException exception
    ) throws IOException, ServletException {
        var status = HttpStatus.FORBIDDEN;
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

}
