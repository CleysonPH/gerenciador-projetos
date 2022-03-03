package com.cleysonph.gerenciadorprojetos.core.validators;

import java.time.LocalDate;
import java.time.Period;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cleysonph.gerenciadorprojetos.core.utils.DateTimeUtils;

public class AgeValidator implements ConstraintValidator<Age, LocalDate> {

    private int min;
    private int max;

    @Override
    public void initialize(Age constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
        validateParameters();
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        var age = calculateAge(value);
        return age >= min && age <= max;
    }

    private int calculateAge(LocalDate value) {
        var today = DateTimeUtils.today();
        return Period.between(value, today).getYears();
    }

    private void validateParameters() {
        if (min < 0) {
            throw new IllegalArgumentException("O parâmetro min não pode ser negativo");
        }
        if (max < 0) {
            throw new IllegalArgumentException("O parâmetro max não pode ser negativo");
        }
        if (max < min) {
            throw new IllegalArgumentException("O pârametro max não pode ser menor que o parâmetro min");
        }
    }

}
