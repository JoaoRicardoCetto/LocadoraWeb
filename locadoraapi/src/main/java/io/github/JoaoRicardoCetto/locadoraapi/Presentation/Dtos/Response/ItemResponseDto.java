package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos;

import java.time.LocalDate;
import java.util.UUID;

public record ItemResponseDto(
        UUID id,
        String tipo,
        LocalDate dataAquisicao,
        double preco,
        TituloResponseDto titulo
) {
}
