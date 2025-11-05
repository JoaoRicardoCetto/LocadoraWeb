package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response;

import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.TituloResponseDto;

import java.util.List;
import java.util.UUID;

public record ClasseResponseDto(
        UUID id,
        String nome,
        double valor,
        int prazoDevolucao,
        List<TituloResponseDto> titulos
) {
}
