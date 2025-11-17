package io.github.JoaoRicardoCetto.locadoraapi.presentation.MappersOld.ControleAcevoMappers;

import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.controleAcervoRequestDtos.DiretorRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos.DiretorResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos.TituloResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Diretor;
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
