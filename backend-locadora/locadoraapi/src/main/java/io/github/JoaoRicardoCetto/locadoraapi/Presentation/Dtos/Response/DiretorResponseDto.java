package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response;

import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.TituloResponseDto;

import java.util.List;
import java.util.UUID;

public record DiretorResponseDto(
        UUID id,
        String nome,
        List<TituloResponseDto> titulos
) {
}
