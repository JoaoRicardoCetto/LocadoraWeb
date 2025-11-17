package io.github.JoaoRicardoCetto.locadoraapi.presentation.mappers;

import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Classe;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.controleAcervoRequestDtos.ClasseRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos.ClasseResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClasseMapper {

    Classe toEntity(ClasseRequestDto requestDto);

    ClasseResponseDto toResponseDto(Classe entity);
}
