package io.github.JoaoRicardoCetto.locadoraapi.Applications.Validators;

import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Repositories.AtorRepository;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Repositories.TituloRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.Ator;
import org.springframework.stereotype.Component;

@Component
public class AtorValidator {

    private final AtorRepository atorRepository;
    private final TituloRepository tituloRepository;


    public AtorValidator(AtorRepository atorRepository, TituloRepository tituloRepository) {
        this.atorRepository = atorRepository;
        this.tituloRepository = tituloRepository;
    }

    public void validar(Ator ator) {

    }

    public boolean possuiTitulo(Ator ator) {
        return tituloRepository.existsByAtores(ator);
    }
}
