package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Mappers;

import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Request.DiretorRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.DiretorResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.TituloResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.Diretor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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
                entity.getTitulos() != null ?
                        entity.getTitulos().stream()
                                .map(titulo -> new TituloResponseDto(
                                        titulo.getId(),
                                        titulo.getNome(),
                                        titulo.getAno(),
                                        titulo.getSinopse(),
                                        titulo.getCategoria(),
                                        null, // Evitar recursão infinita
                                        null, // Evitar recursão infinita
                                        null, // Evitar recursão infinita
                                        null  // itens não incluídos aqui
                                ))
                                .collect(java.util.stream.Collectors.toList())
                        : new ArrayList<>()
        );
    }
}
