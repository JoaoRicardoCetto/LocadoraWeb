package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Mappers;

import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.AtorRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.AtorResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.TituloResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.Ator;
import org.springframework.stereotype.Component;

@Component
public class AtorMapper {

    public Ator toEntity(AtorRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        
        return new Ator(
                requestDto.nome(),
                null // Títulos serão associados posteriormente
        );
    }

    public AtorResponseDto toResponseDto(Ator entity) {
        if (entity == null) {
            return null;
        }
        
        return new AtorResponseDto(
                entity.getId(),
                entity.getNome(),
                null // Evitar recursão infinita - títulos serão carregados separadamente se necessário
        );
    }

    public AtorResponseDto toResponseDtoWithTitulos(Ator entity) {
        if (entity == null) {
            return null;
        }
        
        return new AtorResponseDto(
                entity.getId(),
                entity.getNome(),
                entity.getTitulos().stream()
                        .map(titulo -> new TituloResponseDto(
                                titulo.getId(),
                                titulo.getNome(),
                                titulo.getAno(),
                                titulo.getSinopse(),
                                titulo.getCategoria(),
                                null, // Evitar recursão infinita
                                null, // Evitar recursão infinita
                                null  // Evitar recursão infinita
                        ))
                        .collect(java.util.stream.Collectors.toSet())
        );
    }
}
