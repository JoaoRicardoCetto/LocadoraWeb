package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response;

import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.TituloResponseDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ClasseResponseDto(
        UUID id,
        String nome,
        double valor,
        LocalDate prazoDevolucao,
        List<TituloResponseDto> titulos
) {
}
