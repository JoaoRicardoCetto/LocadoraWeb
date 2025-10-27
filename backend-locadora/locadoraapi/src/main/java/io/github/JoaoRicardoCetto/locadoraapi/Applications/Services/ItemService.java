package io.github.JoaoRicardoCetto.locadoraapi.Applications.Services;

import io.github.JoaoRicardoCetto.locadoraapi.Applications.Common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.Item;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Repositories.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends BaseService<Item> {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
    }

    @Override
    protected IBaseRepository<Item> getRepository() {
        return this.itemRepository;
    }
}
