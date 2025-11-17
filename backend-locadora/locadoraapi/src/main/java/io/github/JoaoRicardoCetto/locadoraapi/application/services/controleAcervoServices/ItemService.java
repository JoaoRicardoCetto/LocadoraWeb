package io.github.JoaoRicardoCetto.locadoraapi.application.services.controleAcervoServices;

import io.github.JoaoRicardoCetto.locadoraapi.application.services.common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Item;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService extends BaseService<Item> {

    private final ItemRepository itemRepository;

    @Override
    protected IBaseRepository<Item> getRepository() {

        return this.itemRepository;
    }

    public List<Item> findByTituloId(UUID tituloId) {
        return itemRepository.findByTituloId(tituloId);
    }

}
