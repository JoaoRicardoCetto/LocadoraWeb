package io.github.JoaoRicardoCetto.locadoraapi.controller.Dtos;

import io.github.JoaoRicardoCetto.locadoraapi.model.Ator;

import java.util.UUID;

public record AtorDto(UUID id, String nome){

    public Ator mapearParaAtor(){
        Ator ator = new Ator();
        ator.setNome(this.nome);
        return ator;
    }
}
