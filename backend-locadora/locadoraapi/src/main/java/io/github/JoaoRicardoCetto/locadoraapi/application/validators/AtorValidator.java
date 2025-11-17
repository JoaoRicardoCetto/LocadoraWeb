package io.github.JoaoRicardoCetto.locadoraapi.application.validators;

import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.AtorRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.TituloRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Ator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AtorValidator {

    private final AtorRepository atorRepository;
    private final TituloRepository tituloRepository;

    public void validar(Ator ator) {

    }

    public boolean possuiTitulo(Ator ator) {
        return tituloRepository.existsByAtores(ator);
    }
}
