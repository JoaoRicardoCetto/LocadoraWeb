package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos;

import java.util.Set;
import java.util.UUID;

public record AtorResponseDto(
        UUID id,
        String nome,
        Set<TituloResponseDto> titulos
) {
}
