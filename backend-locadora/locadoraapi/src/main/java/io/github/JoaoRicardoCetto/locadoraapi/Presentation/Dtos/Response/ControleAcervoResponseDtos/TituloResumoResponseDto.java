package io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos;

import java.util.UUID;

public record TituloResumoResponseDto(
        UUID id,
        String nome
) {
}

