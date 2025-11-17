package io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos;

import java.time.LocalDate;
import java.util.UUID;

public record ItemResponseDto(
        UUID id,
        String tipo,
        LocalDate dataAquisicao,
        int numSerie,
        TituloResponseDto titulo
) {
}
