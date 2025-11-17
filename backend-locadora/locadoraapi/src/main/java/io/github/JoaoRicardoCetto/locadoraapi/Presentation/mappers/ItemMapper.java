package io.github.JoaoRicardoCetto.locadoraapi.presentation.mappers;

import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Item;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.controleAcervoRequestDtos.ItemRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos.ItemResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toEntity(ItemRequestDto requestDto);

    ItemResponseDto toResponseDto(Item item);

}
