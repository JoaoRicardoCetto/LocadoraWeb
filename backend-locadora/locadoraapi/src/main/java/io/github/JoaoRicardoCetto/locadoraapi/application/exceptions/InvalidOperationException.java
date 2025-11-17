package io.github.JoaoRicardoCetto.locadoraapi.application.exceptions;

public class InvalidOperationException extends RuntimeException{
    public InvalidOperationException(String message) {
        super(message);
    }
}
