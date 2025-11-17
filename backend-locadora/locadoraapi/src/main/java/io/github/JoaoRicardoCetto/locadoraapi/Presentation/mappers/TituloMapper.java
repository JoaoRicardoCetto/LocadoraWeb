package io.github.JoaoRicardoCetto.locadoraapi.presentation.mappers;

import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Titulo;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.controleAcervoRequestDtos.TituloRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos.TituloResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TituloMapper {

    Titulo toEntity(TituloRequestDto requestDto);

    TituloResponseDto toResponseDto(Titulo entity);

}
