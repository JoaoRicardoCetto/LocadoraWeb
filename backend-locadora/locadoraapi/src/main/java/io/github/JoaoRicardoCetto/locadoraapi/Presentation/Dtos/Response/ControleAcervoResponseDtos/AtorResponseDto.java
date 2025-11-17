package io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos;

import java.util.Set;
import java.util.UUID;

public record AtorResponseDto(
        UUID id,
        String nome,
        Set<TituloResponseDto> titulos
) {
}
