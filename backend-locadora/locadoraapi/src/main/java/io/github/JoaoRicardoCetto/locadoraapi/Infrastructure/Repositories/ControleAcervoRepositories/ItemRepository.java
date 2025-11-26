package io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories;

import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Item;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends IBaseRepository<Item> {
    List<Item> findByTituloId(UUID tituloId);
    boolean existsByTituloId(UUID tituloId);
}
