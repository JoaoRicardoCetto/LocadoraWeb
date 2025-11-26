package io.github.JoaoRicardoCetto.locadoraapi.application.validators;

import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.DiretorRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.TituloRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DiretorValidator {
    private final DiretorRepository diretorRepository;
    private final TituloRepository tituloRepository;


    public boolean possuiTitulo(UUID diretorId) {
        return tituloRepository.existsByDiretorId(diretorId);
    }
}
