package com.cleysonph.gerenciadorprojetos.api.v1.projeto.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cleysonph.gerenciadorprojetos.core.repositories.ProjetoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProjetoExistsByIdValidator implements ConstraintValidator<ProjetoExistsById, Long> {

    private final ProjetoRepository projetoRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return projetoRepository.existsById(value);
    }

}
