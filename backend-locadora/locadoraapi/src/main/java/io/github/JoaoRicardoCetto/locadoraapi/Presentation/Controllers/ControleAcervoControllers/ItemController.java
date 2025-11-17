package io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.ControleAcervoControllers;

import io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.BaseController;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.controleAcervoRequestDtos.ItemRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos.ItemResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.MappersOld.ControleAcevoMappers.ItemMapper;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Item;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.controleAcervoServices.ItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("itens")
public class ItemController extends BaseController<Item, ItemService, ItemRequestDto, ItemResponseDto> {

    private final ItemMapper itemMapper;

    public ItemController(ItemService service, ItemMapper itemMapper) {
        super(service);
        this.itemMapper = itemMapper;
    }

    @Override
    protected Item toEntity(ItemRequestDto requestDto) {
        return itemMapper.toEntity(requestDto);
    }

    @Override
    protected ItemResponseDto toResponseDto(Item entity) {
        return itemMapper.toResponseDto(entity);
    }

}
