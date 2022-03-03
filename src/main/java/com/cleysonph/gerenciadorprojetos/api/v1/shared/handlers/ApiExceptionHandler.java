package com.cleysonph.gerenciadorprojetos.api.v1.shared.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ErrorResponse;
import com.cleysonph.gerenciadorprojetos.core.exceptions.EntityNotFoundException;
import com.cleysonph.gerenciadorprojetos.core.utils.DateTimeUtils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(
        EntityNotFoundException exception, WebRequest request
    ) {
        return createResponseEntity(
            exception.getLocalizedMessage(),
            exception.getClass().getSimpleName(),
            HttpStatus.NOT_FOUND,
            request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException exception,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
    ) {
        return createResponseEntity(
            exception.getClass().getSimpleName(),
            "Houveram erros de validação",
            status,
            request,
            convertFieldErrors(exception.getBindingResult().getFieldErrors())
        );
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        HttpMessageNotReadableException exception,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
    ) {
        return createResponseEntity(
            exception.getLocalizedMessage(),
            exception.getClass().getSimpleName(),
            status,
            request
        );
    }

    private Map<String, List<String>> convertFieldErrors(List<FieldError> fieldsErrors) {
        var errors = new HashMap<String, List<String>>();
        fieldsErrors.stream().forEach(fieldError -> {
            var field = fieldError.getField();
            var message = fieldError.getDefaultMessage();
            if (errors.containsKey(field)) {
                errors.get(field).add(message);
            } else {
                var fieldErrors = new ArrayList<String>();
                fieldErrors.add(message);
                errors.put(field, fieldErrors);
            }
        });
        return errors;
    }

    private ResponseEntity<Object> createResponseEntity(
        String message,
        String cause,
        HttpStatus status,
        WebRequest request,
        Map<String, List<String>> errors
    ) {
        var path = ((ServletWebRequest)request).getRequest().getRequestURI();
        var body = ErrorResponse.builder()
            .error(status.getReasonPhrase())
            .cause(cause)
            .status(status.value())
            .timestamp(DateTimeUtils.now())
            .message(message)
            .path(path)
            .errors(errors)
            .build();
        return new ResponseEntity<>(body, status);
    }

    private ResponseEntity<Object> createResponseEntity(
        String message,
        String cause,
        HttpStatus status,
        WebRequest request
    ) {
        return createResponseEntity(message, cause, status, request, null);
    }

}
