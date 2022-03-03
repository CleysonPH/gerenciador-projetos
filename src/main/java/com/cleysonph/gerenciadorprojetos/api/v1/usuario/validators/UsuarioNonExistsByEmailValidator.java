package com.cleysonph.gerenciadorprojetos.api.v1.usuario.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.utils.RoutePathVariablesUtils;
import com.cleysonph.gerenciadorprojetos.core.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioNonExistsByEmailValidator implements
    ConstraintValidator<UsuarioNonExistsByEmail, String> {

    private final UsuarioRepository usuarioRepository;
    private final RoutePathVariablesUtils routePathVariablesUtils;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }

        var usuarioId = routePathVariablesUtils.getRoutePathVariables().getUsuarioId();
        if (usuarioId == null) {
            return !usuarioRepository.existsByEmail(value);
        }
        return !usuarioRepository.existsByEmail(value)
            || usuarioRepository.existsByEmailAndId(value, usuarioId);
    }

}
