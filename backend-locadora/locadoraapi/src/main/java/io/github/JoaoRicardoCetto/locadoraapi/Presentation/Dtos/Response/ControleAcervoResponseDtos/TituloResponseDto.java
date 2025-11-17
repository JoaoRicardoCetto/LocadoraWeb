package io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos;

import java.time.LocalDate;
import java.util.Set;
import java.util.List;
import java.util.UUID;

public record TituloResponseDto(
        UUID id,
        String nome,
        LocalDate ano,
        String sinopse,
        String categoria,
        DiretorResponseDto diretor,
        ClasseResponseDto classe,
        Set<AtorResponseDto> atores,
        List<ItemResponseDto> itens
) {
}
