package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Mappers;

import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Request.ClasseRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.ClasseResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.TituloResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.Classe;
import org.springframework.stereotype.Component;

@Component
public class ClasseMapper {

    public Classe toEntity(ClasseRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        
        return new Classe(
                requestDto.nome(),
                requestDto.valor(),
                requestDto.prazoDevolucao(),
                null // Títulos serão associados posteriormente
        );
    }

    public ClasseResponseDto toResponseDto(Classe entity) {
        if (entity == null) {
            return null;
        }
        
        return new ClasseResponseDto(
                entity.getId(),
                entity.getNome(),
                entity.getValor(),
                entity.getPrazoDevolucao(),
                null // Evitar recursão infinita - títulos serão carregados separadamente se necessário
        );
    }

    public ClasseResponseDto toResponseDtoWithTitulos(Classe entity) {
        if (entity == null) {
            return null;
        }
        
        return new ClasseResponseDto(
                entity.getId(),
                entity.getNome(),
                entity.getValor(),
                entity.getPrazoDevolucao(),
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
