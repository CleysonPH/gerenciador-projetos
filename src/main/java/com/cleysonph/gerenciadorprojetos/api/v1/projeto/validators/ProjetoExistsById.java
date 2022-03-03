package com.cleysonph.gerenciadorprojetos.api.v1.projeto.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProjetoExistsByIdValidator.class)
public @interface ProjetoExistsById {

    String message() default "projeto de id ${validatedValue} não existe";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
