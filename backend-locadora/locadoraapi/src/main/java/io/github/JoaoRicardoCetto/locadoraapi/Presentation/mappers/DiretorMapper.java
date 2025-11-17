package io.github.JoaoRicardoCetto.locadoraapi.presentation.mappers;

import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Diretor;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.controleAcervoRequestDtos.DiretorRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos.DiretorResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiretorMapper {

    Diretor toEntity(DiretorRequestDto requestDto);

    DiretorResponseDto toResponseDto(Diretor diretor);
}
