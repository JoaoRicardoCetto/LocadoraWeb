package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Controllers.ControleAcervoControllers;

import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Controllers.BaseController;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Request.ControleAcervoRequestDtos.ItemRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.ControleAcervoResponseDtos.ItemResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Mappers.ControleAcevoMappers.ItemMapper;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.ControleAcervo.Item;
import io.github.JoaoRicardoCetto.locadoraapi.Applications.Services.ControleAcervoServices.ItemService;
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
