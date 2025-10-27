package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEx handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){

        List<FieldError> fieldErrors = ex.getFieldErrors();

        List<FieldEx> listaErros = fieldErrors
                .stream()
                .map(fe -> new FieldEx(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ResponseEx(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação.",
                listaErros
        );
    }
}
