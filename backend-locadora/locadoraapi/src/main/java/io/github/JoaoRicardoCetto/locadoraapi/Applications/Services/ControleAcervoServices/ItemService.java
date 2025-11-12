package io.github.JoaoRicardoCetto.locadoraapi.Applications.Services.ControleAcervoServices;

import io.github.JoaoRicardoCetto.locadoraapi.Applications.Common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.ControleAcervo.Item;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Repositories.ControleAcervoRepositories.ItemRepository;
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
