package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record TituloResponseDto(
        UUID id,
        String nome,
        LocalDate ano,
        String sinopse,
        String categoria,
        DiretorResponseDto diretor,
        ClasseResponseDto classe,
        Set<AtorResponseDto> atores
) {
}
