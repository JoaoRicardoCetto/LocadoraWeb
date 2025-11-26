package io.github.JoaoRicardoCetto.locadoraapi.application.validators;

import io.github.JoaoRicardoCetto.locadoraapi.application.exceptions.TituloDeleteException;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TituloValidator {
    private final ItemRepository itemRepository;

    public boolean possuiItem(UUID tituloId){

        return itemRepository.existsByTituloId(tituloId);
    }
}
