package io.github.JoaoRicardoCetto.locadoraapi.repository;

import io.github.JoaoRicardoCetto.locadoraapi.model.Titulo;
import io.github.JoaoRicardoCetto.locadoraapi.model.Ator;

import java.util.List;
import java.util.UUID;

public interface AtorRepository extends IBaseRepository<Ator, UUID> {
    List<Ator> findByNome(String nome);
}
