package com.cleysonph.gerenciadorprojetos.api.v1.usuario.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsUsuarioValidator.class)
public @interface IsUsuario {

    String message() default "usuário de id ${validatedValue} não é do tipo USUARIO";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
