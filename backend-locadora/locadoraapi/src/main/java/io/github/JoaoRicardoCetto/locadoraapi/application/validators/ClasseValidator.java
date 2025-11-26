package io.github.JoaoRicardoCetto.locadoraapi.application.validators;

import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.ClasseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.TituloRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Classe;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClasseValidator {

    private final ClasseRepository classeRepository;
    private final TituloRepository tituloRepository;

    public boolean possuiTitulo(UUID classeId) {

        return tituloRepository.existsByClasseId(classeId);
    }
}
