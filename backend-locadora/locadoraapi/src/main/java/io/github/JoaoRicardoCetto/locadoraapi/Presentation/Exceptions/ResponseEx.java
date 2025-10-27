package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ResponseEx(int status, String message, List<FieldEx> erros) {

    public static ResponseEx respostaPadrao(String mensagem){
        return new ResponseEx(HttpStatus.BAD_REQUEST.value(), mensagem, List.of());
    }

    public static ResponseEx conflito(String mensagem){
        return new ResponseEx(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }
}
