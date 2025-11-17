package io.github.JoaoRicardoCetto.locadoraapi.presentation.mappers;

import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.AtorRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Ator;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.controleAcervoRequestDtos.AtorRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos.AtorResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class AtorMapper {

    @Autowired
    protected AtorRepository repository;

    public abstract Ator toEntity(AtorRequestDto requestDto);

    public abstract AtorResponseDto toResponseDto(Ator entity);

}
