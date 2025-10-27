package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response;

import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.TituloResponseDto;

import java.util.Set;
import java.util.UUID;

public record AtorResponseDto(
        UUID id,
        String nome,
        Set<TituloResponseDto> titulos
) {
}
