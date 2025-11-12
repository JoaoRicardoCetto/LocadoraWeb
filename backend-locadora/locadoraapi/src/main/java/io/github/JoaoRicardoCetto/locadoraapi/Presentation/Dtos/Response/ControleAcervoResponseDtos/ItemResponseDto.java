package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.ControleAcervoResponseDtos;

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
