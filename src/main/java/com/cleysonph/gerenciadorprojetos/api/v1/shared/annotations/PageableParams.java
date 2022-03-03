package com.cleysonph.gerenciadorprojetos.api.v1.shared.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
    @ApiImplicitParam(name = "page", allowMultiple = false, dataType = "int", paramType = "query", defaultValue = "0", value = "Número da página que deseja recuperar (0..N)"),
    @ApiImplicitParam(name = "size", allowMultiple = false, dataType = "int", paramType = "query", defaultValue = "20", value = "Número de elementos por página."),
    @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Criterio de ordenação no formato: propriedade(,asc|desc).")
})
public @interface PageableParams {}