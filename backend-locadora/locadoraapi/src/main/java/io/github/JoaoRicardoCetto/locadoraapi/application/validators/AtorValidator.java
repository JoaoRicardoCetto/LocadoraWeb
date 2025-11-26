package io.github.JoaoRicardoCetto.locadoraapi.application.validators;

import io.github.JoaoRicardoCetto.locadoraapi.application.exceptions.InvalidOperationException;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.AtorRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.TituloRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Ator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AtorValidator {

    private final AtorRepository atorRepository;
    private final TituloRepository tituloRepository;

    public boolean validar(Ator ator) {

        if(ator.getNome().length() < 3){
            return false;
        }

        return true;
    }

    public boolean possuiTitulo(UUID atorId) {

        return tituloRepository.existsByAtoresId(atorId);
    }
}
