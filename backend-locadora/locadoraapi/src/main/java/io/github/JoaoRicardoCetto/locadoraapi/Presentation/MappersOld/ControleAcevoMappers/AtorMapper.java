package io.github.JoaoRicardoCetto.locadoraapi.presentation.MappersOld.ControleAcevoMappers;

import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.controleAcervoRequestDtos.AtorRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos.AtorResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos.TituloResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Ator;
import org.springframework.stereotype.Component;

@Component
public class AtorMapper {

    public Ator toEntity(AtorRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        
        return new Ator(
                requestDto.nome(),
                new java.util.HashSet<>() // Cria Set vazio para evitar atribuição de null
        );
    }

    public AtorResponseDto toResponseDto(Ator entity) {
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
                null, // Evitar recursão infinita
                null  // itens não incluídos aqui
            ))
            .collect(java.util.stream.Collectors.toSet())
    );
    }

}
