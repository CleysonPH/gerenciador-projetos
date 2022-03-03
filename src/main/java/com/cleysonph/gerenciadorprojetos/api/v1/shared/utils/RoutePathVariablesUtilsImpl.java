package com.cleysonph.gerenciadorprojetos.api.v1.shared.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoutePathVariablesUtilsImpl implements RoutePathVariablesUtils {

    private static final String PATH_VARIABLE_ATTRIBUTE_KEY = "org.springframework.web.servlet.View.pathVariables";

    private final ObjectMapper objectMapper;

    @Override
    public RoutePathVariables getRoutePathVariables() {
        var currentRequestAttributes = RequestContextHolder.currentRequestAttributes();
        var attribute = currentRequestAttributes.getAttribute(PATH_VARIABLE_ATTRIBUTE_KEY, RequestAttributes.SCOPE_REQUEST);
        var routePathVariables = objectMapper.convertValue(attribute, RoutePathVariables.class);
        return routePathVariables == null ? new RoutePathVariables() : routePathVariables;
    }

}
