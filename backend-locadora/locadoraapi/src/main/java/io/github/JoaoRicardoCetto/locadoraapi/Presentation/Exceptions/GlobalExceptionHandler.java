package io.github.JoaoRicardoCetto.locadoraapi.presentation.exceptions;


import io.github.JoaoRicardoCetto.locadoraapi.application.exceptions.*;
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
    public ResponseEx handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        List<FieldEx> listaErros = fieldErrors.stream()
                .map(fe -> new FieldEx(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEx(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                ex.getMessage(),
                listaErros
        );
    }

    @ExceptionHandler({
            AtorDeleteException.class,
            ClasseDeleteException.class,
            DiretorDeleteException.class,
            TituloDeleteException.class,
            DependenteCreateException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEx handleConflictExceptions(RuntimeException ex) {
        return ResponseEx.conflito(ex.getMessage());
    }

    @ExceptionHandler(InvalidOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEx handleInvalidOperation(InvalidOperationException ex){
        return ResponseEx.respostaPadrao(ex.getMessage());
    }

}
