package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Mappers;

import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.DiretorRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.DiretorResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.TituloResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.Diretor;
import org.springframework.stereotype.Component;

@Component
public class DiretorMapper {

    public Diretor toEntity(DiretorRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        
        return new Diretor(
                requestDto.nome(),
                null // Títulos serão associados posteriormente
        );
    }

    public DiretorResponseDto toResponseDto(Diretor entity) {
        if (entity == null) {
            return null;
        }
        
        return new DiretorResponseDto(
                entity.getId(),
                entity.getNome(),
                null // Evitar recursão infinita - títulos serão carregados separadamente se necessário
        );
    }

    public DiretorResponseDto toResponseDtoWithTitulos(Diretor entity) {
        if (entity == null) {
            return null;
        }
        
        return new DiretorResponseDto(
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
                        .collect(java.util.stream.Collectors.toList())
        );
    }
}
