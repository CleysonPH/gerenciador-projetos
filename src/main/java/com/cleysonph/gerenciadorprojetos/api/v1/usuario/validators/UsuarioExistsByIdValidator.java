package com.cleysonph.gerenciadorprojetos.api.v1.usuario.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cleysonph.gerenciadorprojetos.core.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioExistsByIdValidator implements ConstraintValidator<UsuarioExistsById, Long> {

    private final UsuarioRepository usuarioRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return usuarioRepository.existsById(value);
    }

}
