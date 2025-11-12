package io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Repositories.ControleAcervoRepositories;

import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.ControleAcervo.Item;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends IBaseRepository<Item> {
    List<Item> findByTituloId(UUID tituloId);
}
